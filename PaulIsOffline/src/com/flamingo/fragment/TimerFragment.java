package com.flamingo.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.flamingo.object.Timertron;
import com.flamingo.paulisoffline.R;

public class TimerFragment extends SherlockFragment {
	private View fragView;
	private TextView tvDays, tvHours, tvMinutes;
	private Handler handler;
	private Timertron timeTicker;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		fragView = inflater.inflate(R.layout.fragment_timer, null);

		setupComponents();

		setupTimer();

		return fragView;
	}

	/**
	 * Sets up the Timertron.
	 */
	private void setupTimer() {
		long targetMillis = 0;
		
		try {
			String strDate = "May 01 2013 00:01";
			SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy HH:mm", Locale.UK);
			Date targetDate = formatter.parse(strDate);
			targetMillis = targetDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		timeTicker = new Timertron(targetMillis);
		
		setupTicker();
	}

	/**
	 * Sets the component variables
	 */
	private void setupComponents() {
		tvDays = (TextView) fragView.findViewById(R.id.tvTimerDays);
		tvHours = (TextView) fragView.findViewById(R.id.tvTimerHours);
		tvMinutes = (TextView) fragView.findViewById(R.id.tvTimerMinutes);
	}
	
	/**
	 * Updates the components with new values from the Timertron
	 */
	private void updateComponents() {
		if (timeTicker != null) {
			tvDays.setText("" + timeTicker.getTargetDays());
			tvHours.setText("" + timeTicker.getTargetHours());
			tvMinutes.setText("" + timeTicker.getTargetMinutes());
		}
	}

	/**
	 * Sets up the timer ticker to automatically update.
	 */
	private void setupTicker() {
		handler = new Handler();
		handler.post(new Runnable() {
			@Override
			public void run() {
				timeTicker.calculateDifference();
				updateComponents();
				
				// Here we are setting this Runnable to run again every 800 milliseconds
				// If I wasn't lazy, I would do these calculations off the main thread
				// and use a Observer to find when updates occur. But I am lazy, so deal with it.
				handler.postDelayed(this, 800);
			}
		});
	}
}
