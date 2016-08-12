package com.example.user.simpleui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment; //import android.support.v4.app.Fragment;  為了支援之前的API
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DialerFilter;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DrinkOrderDialog.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DrinkOrderDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinkOrderDialog extends DialogFragment //繼承後就會變成子頁面
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DrinkOrderDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrinkOrderDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static DrinkOrderDialog newInstance(String param1, String param2) {//希望new出來的東西是現做設定，不讓他人發現
        DrinkOrderDialog fragment = new DrinkOrderDialog();
        Bundle args = new Bundle(); //Bundle會攜帶所需的變數
        args.putString(ARG_PARAM1, param1); //給onCreate的變數
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args); //會被攜帶到onCreate被讀取(記憶體區塊Bundle)
        return fragment;
    } //須事先做的設定

    //用了DialogFragment就不用用下面的了，因為內部就有了

//    @Override
//    public void onCreate(Bundle savedInstanceState) { //從activity拿到資料
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1); //從Bundle拿出變數
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) { //設定layout
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_drink_order_dialog, container, false); //將fragment_drink_order_dialog，lay(new)出來
//        //可從這拿到UI conponent(下面程式碼)
//        /*View view = inflater.inflate(R.layout.fragment_drink_order_dialog, container, false);
//                TextView textview = view;*/
//    }

    @Override //把 onCreate 跟onCreateView包起來
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        if(getArguments() != null)
        {

        }

        View contentView = getActivity().getLayoutInflater().inflate(R.layout.fragment_drink_order_dialog,null); //dialog 內包含的內容
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //會依照他的架構popup他的視窗
        builder.setView(contentView)
                .setTitle("Hello Dailog")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }); //PositiveButton: 確定這設定或取消
        return builder.create();
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) { //真正溝通的時候
        if (mListener != null) { //確認是否有會的人存在，才可實作onFragmentInteraction
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) //context => activity傳入，建立橋梁
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) //判斷activity會不會這個介面
        {
            mListener = (OnFragmentInteractionListener) context; //僅能辨認是否會這個頁面，並無法知道是activity
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() { //取消任何溝通的東西，記憶體會被釋放掉
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener //定義了一個介面，需實作下面function，建立橋梁
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
