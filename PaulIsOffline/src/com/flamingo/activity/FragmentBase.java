package com.flamingo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.flamingo.fragment.AboutDialogFragment;
import com.flamingo.fragment.TimerFragment;
import com.flamingo.paulisoffline.R;

public class FragmentBase extends SherlockFragmentActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_holder);
		
		setupActionBar();
		
		navigate();
	}

	private void setupActionBar() {
		ActionBar ab = getSupportActionBar();
		ab.setTitle(getString(R.string.app_name));
	}
	
	private void navigate() {
		Fragment frag = new TimerFragment();

		final FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		frag.setArguments(getIntent().getExtras());

		transaction.replace(R.id.frag_holder, frag).addToBackStack("" + 0);
		transaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
		transaction.commitAllowingStateLoss();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getSupportMenuInflater();
        mi.inflate(R.menu.root_menu, menu);
        
        return true;
    }
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		
		if (item.getItemId() == R.id.menu_about) {
			AboutDialogFragment urlD = new AboutDialogFragment();
			urlD.show(getSupportFragmentManager(), "ABOUT");
		}
		
		return false;
	}
}
