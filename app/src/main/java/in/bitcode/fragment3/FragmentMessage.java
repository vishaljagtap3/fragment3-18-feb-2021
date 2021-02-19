package in.bitcode.fragment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMessage extends Fragment implements FragmentData.OnDataSetListener{

    private EditText mEdtMessage;
    private Button mBtnSendMessage;
    private String mMessage;

    public interface OnMessageSentListener {
        public void onMessageSent(FragmentMessage fragmentMessage, String message);
    }

    private OnMessageSentListener mOnMessageSentListener;

    public void setOnMessageSentListener(OnMessageSentListener onMessageSentListener) {
        this.mOnMessageSentListener = onMessageSentListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);

        mEdtMessage = view.findViewById(R.id.edtMessage);
        mBtnSendMessage = view.findViewById(R.id.btnSendMessage);

        mBtnSendMessage.setOnClickListener(new BtnSendMessageClickListener());

        return view;
    }

    private class BtnSendMessageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            //way 1
            //((MainActivity)getActivity()).sendMessageToDataFragment( mEdtMessage.getText().toString());

            //way2
            //((FragmentData)getFragmentManager().findFragmentById(R.id.fragmentData))
              //      .setData(mEdtMessage.getText().toString());

            //way 3 (Recommended)
            if(mOnMessageSentListener != null) {
                mOnMessageSentListener.onMessageSent(
                        FragmentMessage.this,
                        mEdtMessage.getText().toString()
                );
            }

        }
    }

    @Override
    public void onDataSet(FragmentData fragmentData, String data) {
        mMessage = data;
        mEdtMessage.setText(data);
    }

    public void setMessage(String message) {
        mMessage = message;
        mEdtMessage.setText(message);
    }
}
