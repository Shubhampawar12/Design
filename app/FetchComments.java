import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchComments  extends AsyncTask<Void,Void,String> {
    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url=new URL(" https://jsonplaceholder.typicode.com/comments");
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line;

            while((line= reader.readLine()) !=null){
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
