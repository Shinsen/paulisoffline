package com.flamingo.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.flamingo.paulisoffline.R;

public class AboutDialogFragment extends SherlockDialogFragment {
	
	private TextView notice;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater ll = getActivity().getLayoutInflater();
		ScrollView dialog = (ScrollView) ll.inflate(R.layout.dialog_about, null);
		
		notice = (TextView) dialog.findViewById(R.id.tvAboutLicense);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(dialog)
				.setPositiveButton(
						getActivity().getString(
								R.string.about_dismiss),
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								AboutDialogFragment.this.getDialog().dismiss();
							}

						}).setNegativeButton(getActivity().getString(R.string.about_visit), new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent viewSite = new Intent(Intent.ACTION_VIEW);
								viewSite.setData(Uri.parse("http://flamin.co/"));
								startActivity(viewSite);
								
								AboutDialogFragment.this.getDialog().dismiss();
							}
						});

		LoadOSSNotice ossLoad = new LoadOSSNotice();
		ossLoad.execute();
		
		return builder.create();
	}
	
	private class LoadOSSNotice extends AsyncTask<Void, Void, Void> {
		Context c;
		String out = "";
		
		protected void onPreExecute() {
			super.onPreExecute();
			
			c = AboutDialogFragment.this.getActivity();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				InputStream io = c.getAssets().open("oss_notice.txt");
				InputStreamReader ior = new InputStreamReader(io);
				int line = ior.read();
				while (line != -1) {
					out += (char) line;
					line = ior.read();
				}
				
				ior.close();
			} catch (IOException e) {
				out = ":(";
				
				e.printStackTrace();
			}

			return null;
		}
		
		protected void onPostExecute(Void result) {
			notice.setText(out);
			
			super.onPostExecute(result);
		}
		
	}
}