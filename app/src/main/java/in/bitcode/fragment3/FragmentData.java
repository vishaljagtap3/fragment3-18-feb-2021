package in.bitcode.fragment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import in.bitcode.fragment3.FragmentMessage.OnMessageSentListener;

public class FragmentData extends Fragment {

    private TextView mTxtData;
    private Button mBtnSendData;

    private String mData;

    public interface OnDataSetListener {
        void onDataSet(FragmentData fragmentData, String data);
    }

    private OnDataSetListener mOnDataSetListener;

    public void setOnDataSetListener(OnDataSetListener onDataSetListener) {
        this.mOnDataSetListener = onDataSetListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, null);

        mTxtData = view.findViewById(R.id.txtData);
        mBtnSendData = view.findViewById(R.id.btnSendData);

        mBtnSendData.setOnClickListener(new BtnSendDataClickListener());

        return view;
    }

    private class BtnSendDataClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //way 1
            //((MainActivity) getActivity()).sendDataToMessageFragment(mData.toUpperCase());

            //way 2
            //((FragmentMessage) getFragmentManager().findFragmentById(R.id.fragmentMessage))
              //      .setMessage(mData.toUpperCase());

            //way 3 (Recommended)
            if(mOnDataSetListener != null) {
                mOnDataSetListener.onDataSet(FragmentData.this, mData.toUpperCase());
            }
        }
    }

    /*@Override
    public void onMessageSent(FragmentMessage fragmentMessage, String message) {
        mData = message;
        mTxtData.setText(message);
    }*/

    public void setData(String data) {
        mData = data;
        mTxtData.setText(data);
    }
}
