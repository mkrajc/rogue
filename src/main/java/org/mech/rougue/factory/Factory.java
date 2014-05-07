package org.mech.rougue.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Factory {

	private static final Logger LOG = LoggerFactory.getLogger(Factory.class);
	private List<Definition> factoryDefinitions = new ArrayList<Definition>(8);

	// default package
	static Map<Class, BeanDefinition> beanDefinitions = new HashMap<Class, BeanDefinition>();
	static Map<String, Bean> beans = new HashMap<String, Bean>();

	public void register(Definition definition) {
		LOG.debug("Register definition " + definition);
		factoryDefinitions.add(definition);
	}

	public void initialize() {
		loadDefinitions();
		initBeans();
		injectDependencies();
		invokePostConstructMethods();
	}

	protected void loadDefinitions() {
		for (Definition definition : factoryDefinitions) {
			LOG.trace("Loading definitions from [" + definition + "]");
			definition.define();
		}
	}

	protected void initBeans() {
		LOG.debug("starting initialiazing beans");

		for (BeanDefinition bDef : beanDefinitions.values()) {
			LOG.trace("Init [" + bDef.clazz + "]");
			if (bDef.factory) {
				LOG.trace("Skipping factory: " + bDef);
			} else {
				final Bean bean = createBean(bDef);
				beans.put(bean.name, bean);
			}
		}
	}

	protected void invokePostConstructMethods() {
		LOG.debug("invoking @PostConstruct methods");

		for (Bean bean : beans.values()) {
			invokePostConstructMethod(bean);
		}
	}

	protected static void invokePostConstructMethod(Bean bean) {
		Method postConstructMethod = bean.definition.postConstructMethod;
		if (postConstructMethod != null) {
			final String methodDescription = bean.definition.clazz.getSimpleName() + "." + postConstructMethod.getName();

			LOG.trace("invoking @PostConstruct [" + methodDescription + " ] ");

			try {
				postConstructMethod.invoke(bean.value);
			} catch (Exception e) {
				throw new IllegalArgumentException("Invoking method [" + methodDescription + "] failed", e);
			}
		}
	}

	protected void injectDependencies() {
		LOG.debug("injecting dependencies");
		for (Bean bean : beans.values()) {
			injectDependencies(bean);
		}
	}

	protected static void injectDependencies(Bean bean) {
		final List<Field> dependencies = bean.definition.dependencies;
		if (dependencies != null) {
			LOG.trace("injecting dependencies into [" + bean + "]");
			try {
				for (Field f : dependencies) {
					f.setAccessible(true);
					final Class<?> type = f.getType();

					if (Collection.class.isAssignableFrom(type)) {
						injectOfCollectionType(type, f, bean);
					} else {
						injectBean(type, f, bean);
					}

				}
			} catch (Exception e) {
				throw new IllegalArgumentException("Injecting into [" + bean + "] failed", e);
			}
		}
	}

	private static void injectBean(Class type, Field field, Bean bean) throws IllegalArgumentException, IllegalAccessException {
		final String dependecyDescription = bean.definition.clazz.getSimpleName() + "." + field.getName();

		final List<Bean> dependency = getBeansOfType(type);

		if (dependency == null || dependency.size() == 0) {
			throw new IllegalArgumentException("Dependency [" + type + "] in [" + dependecyDescription + "] is not defined");
		}

		if (dependency.size() > 1) {
			throw new IllegalArgumentException("More beans of [" + type + "] in [" + dependecyDescription + "] are defined");
		}

		field.set(bean.value, dependency.get(0).value);

	}

	private static void injectOfCollectionType(Class collectionType, Field field, Bean bean) throws IllegalArgumentException, IllegalAccessException {
		final Collection collection = getCollectionInstance(collectionType);
		final Type type = field.getGenericType();

		if (type instanceof ParameterizedType) {
			final ParameterizedType pType = (ParameterizedType) type;
			final Type beanType = pType.getActualTypeArguments()[0];
			final List<Bean> beansOfType = getBeansOfType((Class) beanType);

			if (beansOfType != null) {
				for (Bean depBean : beansOfType) {
					collection.add(depBean.value);
				}
			} else {
				LOG.warn("No beans of type=" + beanType);
			}

			if (collection instanceof List) {
				try {
					Collections.sort((List) collection, new OrderComparator<Ordered>());
					LOG.trace("Collection sorted [" + bean.name + "." + field.getName() + "].");
				} catch (ClassCastException cce) {
					LOG.trace("Collection is not sortable [" + bean.name + "." + field.getName() + "]");
				}
			}

			field.set(bean.value, collection);

		} else {
			throw new IllegalArgumentException("Not defined the paramterized type on collection [" + field.getName() + " of " + collectionType
					+ "] in [" + bean.definition.clazz.getSimpleName() + "]");
		}

	}

	static List<Bean> getBeansOfType(Class beanType) {
		final List list = new ArrayList();

		final Collection<Bean> values = beans.values();
		for (Bean bean : values) {
			if (bean.isOfType(beanType)) {
				list.add(bean);
			}
		}

		if (list.isEmpty()) {
			// search for factories;
			BeanDefinition beanDefinition = beanDefinitions.get(beanType);
			if (beanDefinition != null && beanDefinition.factory) {
				final Bean bean = createBean(beanDefinition);
				injectDependencies(bean);
				invokePostConstructMethod(bean);
				list.add(bean);
			}
		}

		return list.isEmpty() ? null : list;
	}

	static Bean getBeanOfType(Class beanType) {
		final List<Bean> beansOfType = getBeansOfType(beanType);
		return (beansOfType == null || beansOfType.size() > 1) ? null : beansOfType.get(0);
	}

	// public List<Object> getBeans(Class beanType) {
	// final List list = new ArrayList();
	//
	// final Collection<Bean> values = beans.values();
	// for (Bean bean : values) {
	// if (bean.isOfType(beanType)) {
	// list.add(bean.value);
	// }
	// }
	//
	// return list.isEmpty() ? null : list;
	// }
	//
	// public static <T> T getBean(Class<T> beanType) {
	// Bean bean = getBeanOfType(beanType);
	// return (T) (bean == null ? null : bean.value);
	// }

	public static List<Object> getBeans(Class beanType) {
		final List list = new ArrayList();

		final Collection<Bean> values = beans.values();
		for (Bean bean : values) {
			if (bean.isOfType(beanType)) {
				list.add(bean.value);
			}
		}

		return list.isEmpty() ? null : list;
	}

	public static <T> T getBean(Class<T> beanType) {
		Bean bean = getBeanOfType(beanType);
		return (T) (bean == null ? null : bean.value);
	}

	private static Collection getCollectionInstance(Class collectionType) {
		Collection collection = null;

		if (collectionType.equals(List.class)) {
			collection = new ArrayList();
		}

		if (collection == null) {
			throw new IllegalArgumentException("Unsupported collection type: " + collectionType);
		}

		return collection;
	}

	private static Bean createBean(BeanDefinition bDef) {
		return createBeanProvider(bDef).get();
	}

	private static BeanProvider createBeanProvider(BeanDefinition bDef) {
		return new SingletonBeanProvider(bDef);
	}

	private static Object instance(BeanDefinition beanDefinition) {
		try {
			return beanDefinition.constructor.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("TODO change exception Init [" + beanDefinition.clazz.getSimpleName() + "] failed", e);
		}
	}

	@SuppressWarnings("unchecked")
	static class BeanDefinition {
		Constructor constructor;
		Class clazz;
		List<Field> dependencies;
		Method postConstructMethod;
		boolean factory = false;

		void add(Field f) {
			if (dependencies == null) {
				dependencies = new ArrayList<Field>(5);
			}
			dependencies.add(f);
		}

		@Override
		public String toString() {
			return "class=" + clazz.getSimpleName() + (postConstructMethod == null ? "" : ",method=" + postConstructMethod.getName());
		}
	}

	static class Bean {
		BeanDefinition definition;
		String name;
		Object value;

		@Override
		public String toString() {
			return name;
		}

		public boolean isOfType(Class beanType) {
			return beanType.isAssignableFrom(definition.clazz);
		}
	}

	interface BeanProvider {
		Bean get();
	}

	static class SingletonBeanProvider implements BeanProvider {
		private Bean bean;

		SingletonBeanProvider(BeanDefinition bDef) {
			bean = new Bean();
			bean.definition = bDef;
			bean.value = Factory.instance(bDef);
			bean.name = bean.value.getClass().getSimpleName();
		}

		@Override
		public Bean get() {
			return bean;
		}

	}

	static void putBeanDefinition(BeanDefinition beanDefinition) {
		LOG.trace("register beanDefinition [" + beanDefinition + "]");
		beanDefinitions.put(beanDefinition.clazz, beanDefinition);
	}

}
