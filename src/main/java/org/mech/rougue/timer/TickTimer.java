package org.mech.rougue.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TickTimer {
	private static final Logger LOG = LoggerFactory.getLogger(TickTimer.class);

	private static final int TARGET_TPS = 60;
	private static final long NANOS_IN_SEC = 1000000000;

	private TimerRunnable target;
	private String name;

	public TickTimer(String name, TickHandler handler) {
		this(name, handler, TARGET_TPS);
	}

	public TickTimer(String name, TickHandler handler, int targetTps) {
		target = new TimerRunnable(handler, targetTps);
		this.name = name;
	}

	public void stop() {
		target.stopTimer();
	}

	public void start() {
		new Thread(target, name).start();
	}

	private static class TimerRunnable implements Runnable {

		private boolean running = true;
		private double lastTpsTime = 0.;
		private int tps = 0;
		private long optimalTime;

		private TickHandler handler;

		public TimerRunnable(TickHandler handler, int targetTps) {
			this.handler = handler;
			this.optimalTime = NANOS_IN_SEC / targetTps;
		}

		public void stopTimer() {
			running = false;
		}

		private void doUpdate(double delta) {
			handler.onTick(delta);
		}

		@Override
		public void run() {
			long lastLoopTime = System.nanoTime();
			// keep looping round til the game ends
			while (running) {
				// work out how long its been since the last update, this
				// will be used to calculate how far the entities should
				// move this loop
				long now = System.nanoTime();
				long updateLength = now - lastLoopTime;
				lastLoopTime = now;
				double delta = updateLength / ((double) optimalTime);

				// update the frame counter
				lastTpsTime += updateLength;
				tps++;

				// update our FPS counter if a second has passed since
				// we last recorded
				if (lastTpsTime >= NANOS_IN_SEC) {
					LOG.trace("tps: " + tps);
					lastTpsTime = 0;
					tps = 0;
				}

				// update the logic
				doUpdate(delta);

				// we want each frame to take 10 milliseconds, to do this
				// we've recorded when we started the frame. We add 10
				// milliseconds
				// to this and then factor in the current time to give
				// us our final value to wait for
				// remember this is in ms, whereas our lastLoopTime etc. vars
				// are in
				// ns.
				try {
					Thread.sleep((lastLoopTime - System.nanoTime() + optimalTime) / 1000000);
				} catch (Exception e) {}
			}
		}
	}

}
