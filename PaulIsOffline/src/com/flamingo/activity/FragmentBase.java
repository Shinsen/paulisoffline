/*Copyright 2013 Christopher Eggison

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/

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

/**
 * Application base and entry point.
 * 
 * @author Chris Eggison (http://flamin.co)
 *
 */
public class FragmentBase extends SherlockFragmentActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_holder);
		
		setupActionBar();
		
		navigate();
	}

	/**
	 * Configures the ActionBar
	 */
	private void setupActionBar() {
		ActionBar ab = getSupportActionBar();
		ab.setTitle(getString(R.string.app_name));
	}
	
	/**
	 * Handles fragment transactions (switching between fragments).
	 */
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
