package com.dragaosemchama.superthreads;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LongTask extends AsyncTask<Void, Integer, Void>{

    private TextView textInfo;
    private TextView textMoreInfo;
    private ProgressBar progressBar;

    public void setTextInfo(TextView textInfo) {
        this.textInfo = textInfo;
    }

    public void setTextMoreInfo(TextView textMoreInfo) {
        this.textMoreInfo = textMoreInfo;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        if(textInfo != null)
            textInfo.setText("Realizando tarefa demorada...");
        if(textMoreInfo != null)
            textMoreInfo.setText("Clique em Cancelar para terminar a execução");
        if(this.progressBar != null) this.progressBar.setProgress(0);
    }

    @Override
    protected Void doInBackground(Void... params) {

        for(int i=0; i < 1000; i++) {
            try { Thread.sleep(10); }
            catch (InterruptedException e) { e.printStackTrace(); }
            this.publishProgress(i/10);
            if(this.isCancelled()) break;
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        if(this.progressBar != null) this.progressBar.setProgress(progress[0]);
    }

    @Override
    protected void onCancelled() {
        if(textInfo != null)
            textInfo.setText("Tarefa cancelada :(");
        if(textMoreInfo != null)
            textMoreInfo.setText("Clique em iniciar para tentar novamente");
    }

    @Override
    protected void onPostExecute(Void result) {
        if(textInfo != null)
            textInfo.setText("Tarefa realizada com sucesso :)");
        if(textMoreInfo != null)
            textMoreInfo.setText("Clique em iniciar para fazer novamente");
    }
}
