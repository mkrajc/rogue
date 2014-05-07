package org.mech.rougue.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.annotation.PostConstruct;
import org.mech.rougue.factory.Factory.BeanDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDefinition implements Definition {

	private final static Logger LOG = LoggerFactory.getLogger(AbstractDefinition.class);

	public final void define() {
		LOG.info("Loading factory definitions for [" + getClass() + "]");
		definitions();
	}

	public abstract void definitions();

	protected <T> void singleton(Class<T> clazz) {
		Factory.putBeanDefinition(createBeanDefinition(clazz));
	}

	protected <T> void factory(Class<T> clazz) {
		BeanDefinition createBeanDefinition = createBeanDefinition(clazz);
		createBeanDefinition.factory = true;
		Factory.putBeanDefinition(createBeanDefinition);
	}

	protected <T> Constructor<T> getConstructor(Class<T> clazz) throws NoSuchMethodException, SecurityException {
		return clazz.getConstructor();
	}

	protected <T> BeanDefinition createBeanDefinition(Class<T> clazz) {

		try {
			final BeanDefinition bd = new BeanDefinition();
			bd.constructor = getConstructor(clazz);
			bd.clazz = clazz;
			gatherDependencies(clazz, bd);
			scanForPostConstruct(clazz, bd);
			return bd;
		} catch (Exception e) {
			throw new IllegalArgumentException("failed to created bean definition for [" + clazz.getSimpleName() + "]", e);
		}

	}

	private void scanForPostConstruct(Class clazz, BeanDefinition def) {
		final Method[] methods = clazz.getMethods();
		if (methods != null) {
			for (Method postConstrMethod : methods) {
				if (postConstrMethod.getAnnotation(PostConstruct.class) != null) {
					def.postConstructMethod = postConstrMethod;
					break;
				}
			}
		}
	}

	private void gatherDependencies(Class clazz, BeanDefinition def) {
		final Field[] fields = clazz.getDeclaredFields();
		if (fields != null) {
			for (Field f : fields) {
				if (f.getAnnotation(Inject.class) != null) {
					def.add(f);
				}
			}
		}

		if (clazz.getSuperclass() != null) {
			gatherDependencies(clazz.getSuperclass(), def);
		}
	}

}
