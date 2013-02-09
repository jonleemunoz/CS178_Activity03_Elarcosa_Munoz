package com.example.cs178_activity03_elarcosa_munoz;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
//	private String[] image_url = new String[]{
//			"https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-ash3/578602_10151181869434437_1116385294_n.jpg",
//			"https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash3/538877_10151115727799437_1076855672_n.jpg",
//			"https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-ash3/530257_10151086240814437_76839725_n.jpg",
//			"https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-ash3/543041_10150905256689437_768518062_n.jpg",
//			"https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-snc7/421124_10150592493699437_1502873407_n.jpg"
//	};
	
	private String[] image_url = new String[]{
			"http://icons.iconarchive.com/icons/cornmanthe3rd/plex/512/Communication-gmail-icon.png",
			"http://meritworld.com/imgList/Social%20Icons/rss1.png",
			"http://londonballetblog.files.wordpress.com/2012/10/instagram_icon_large.png?w=584",
			"http://www.techpackets.com/wp-content/uploads/2010/09/FaceBook-Icon.png",
			"http://www.ala.org/conferencesevents/sites/ala.org.conferencesevents/files/content/celebrationweeks/natlibraryweek/twitter-icon.png"
	};
	
	private int no_of_files= image_url.length;
    private String URL;
	private ImageView[] imgList = new ImageView[no_of_files];
	private ImageView imageView;
    private Drawable image;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgList[0]=(ImageView) findViewById(R.id.image1);
        imgList[1]=(ImageView) findViewById(R.id.image2);
        imgList[2]=(ImageView) findViewById(R.id.image3);
        imgList[3]=(ImageView) findViewById(R.id.image4);
        imgList[4]=(ImageView) findViewById(R.id.image5);
        for(int i = 0 ; i < no_of_files ; i++ ){
        	imgList[i].setTag(image_url[i]);
        	new FetchFilesTask().execute(imgList[i]);
        }
       	 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private class FetchFilesTask extends AsyncTask<ImageView, Void, Drawable> {

	    private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

	    protected void onPreExecute(){
	        dialog.setMessage("Fetching image from the Internet");
	        dialog.show();
	    }

	     protected Drawable doInBackground(ImageView... args) {

	    	 imageView=args[0];
	         URL = (String) args[0].getTag();    
	    	 image = ImageOperations(URL);
	    	 
	         return image;
	     }

	     protected void onPostExecute(Drawable m_bitmap) {
	         dialog.dismiss();
	         if(m_bitmap != null)
	         {
	        	 imageView.setImageDrawable(m_bitmap);
	         }
	         else
	         {
	        	 imageView.setImageResource(R.drawable.notfound);
	         }
	                
	     }
	     
	     private Drawable ImageOperations(String url) {
	         try {
	             InputStream input = (InputStream) this.fetch(url);
	             Drawable drawable = Drawable.createFromStream(input, "src");
	             return drawable;
	         } catch (MalformedURLException e) {
	             return null;
	         } catch (IOException e) {
	             return null;
	         }
	     }
	     
	     public Object fetch(String addr) throws MalformedURLException,IOException {
	         URL url = new URL(addr);
	         Object content = url.getContent();
	         return content;
	     }
	 }
	
}



	