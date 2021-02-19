package in.bitcode.fragment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentData mFragmentData;
    private FragmentMessage mFragmentMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mFragmentData = (FragmentData) getSupportFragmentManager().findFragmentById(R.id.fragmentData);
        mFragmentMessage = (FragmentMessage) getSupportFragmentManager().findFragmentById(R.id.fragmentMessage);

        mFragmentMessage.setOnMessageSentListener(new MyOnMessageSentListener());

        //mFragmentData.setOnDataSetListener(new MyOnDataSetListener());
        mFragmentData.setOnDataSetListener(mFragmentMessage);
    }


    private class MyOnMessageSentListener implements FragmentMessage.OnMessageSentListener {
        @Override
        public void onMessageSent(FragmentMessage fragmentMessage, String message) {
            mFragmentData.setData(message);
        }
    }

    private class MyOnDataSetListener implements FragmentData.OnDataSetListener {
        @Override
        public void onDataSet(FragmentData fragmentData, String data) {
            mFragmentMessage.setMessage(data);
        }
    }


    public void sendMessageToDataFragment(String message) {
        //code to send message to data fragment
        mFragmentData.setData(message);
    }

    public void sendDataToMessageFragment(String data) {
        mFragmentMessage.setMessage(data);
    }
}















