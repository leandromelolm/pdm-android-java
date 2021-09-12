package br.edu.ifpe.tads.pdm.pratica_rede;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ForecastTask extends AsyncTask<String, Void, List<String>> {
    private final String LOG_TAG = ForecastTask.class.getSimpleName();
    private List<String> forecast = null;
    private ForecastListener listener = null;
    private final String APPID = "a11ac945f2360e8cf7d496e7cb53dc00";
    public ForecastTask(ForecastListener listener) { this.listener = listener; }
    @Override
    protected List<String> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        int noOfDays = 7;
        String locationString = params[0];
        String forecastJson = null;
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https");
            builder.authority("api.openweathermap.org");
            builder.appendEncodedPath("data/2.5/forecast/daily");
            builder.appendQueryParameter("q", locationString);
            builder.appendQueryParameter("mode", "json");
            builder.appendQueryParameter("units","metric");
            builder.appendQueryParameter("cnt","" + noOfDays);
            builder.appendQueryParameter("APPID", APPID);
            URL url = new URL(builder.build().toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                forecastJson = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                forecastJson = null;
            } else {
                forecastJson = buffer.toString();
            }
            forecast = ForecastParser.getDataFromJson(forecastJson, noOfDays);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON Error ", e);
        } finally{
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return forecast;
    }
    @Override
    protected void onPostExecute(List<String> resultStrs) {
        for (String s : resultStrs)
            Log.v(LOG_TAG, "Forecast entry: " + s);

        listener.showForecast(resultStrs);
    }
}
