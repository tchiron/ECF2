package com.example.ecf2;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PollutionFragment extends Fragment {

    private PollutionViewModel mViewModel;
    private TextView tvCity;
//    private TextView tvPm25;
//    private TextView tvPm10;
    private TextView tvAqius;
    private TextView tvAqicn;

    public static PollutionFragment newInstance() {
        return new PollutionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pollution_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mViewModel = ViewModelProviders.of(this).get(PollutionViewModel.class);
        this.tvCity = getActivity().findViewById(R.id.tvCity);
//        this.tvPm25 = getActivity().findViewById(R.id.tvPm25);
//        this.tvPm10 = getActivity().findViewById(R.id.tvPm10);
        this.tvAqius = getActivity().findViewById(R.id.tvAqius);
        this.tvAqicn = getActivity().findViewById(R.id.tvAqicn);

//        Integer pm25 = this.mViewModel.getPm25();
//        if (pm25 == null) {
//            this.mViewModel.setPm25(0);
//        }
//        this.tvPm25.setText(this.mViewModel.getPm25().toString());
//
//        Integer pm10 = this.mViewModel.getPm10();
//        if (pm10 == null) {
//            this.mViewModel.setPm10(0);
//        }
//        this.tvPm10.setText(this.mViewModel.getPm10().toString());

        String aqius = this.mViewModel.getAqius();
        String mainus = this.mViewModel.getMainus();
        if (aqius == null) {
            this.mViewModel.setAqius("NC");
        }
        if (mainus == null) {
            this.mViewModel.setMainus("NC");
        }
        this.tvAqius.setText(String.format("%s (%s)", this.mViewModel.getAqius(), this.mViewModel.getMainus()));

        String aqicn = this.mViewModel.getAqicn();
        String maincn = this.mViewModel.getMaincn();
        if (aqicn == null) {
            this.mViewModel.setAqicn("NC");
        }
        if (maincn == null) {
            this.mViewModel.setMaincn("NC");
        }
        this.tvAqicn.setText(String.format("%s (%s)", this.mViewModel.getAqicn(), this.mViewModel.getMaincn()));

        this.getPollution();
    }

    public void getPollution() {
        PollutionService pollutionService = PollutionService.builder();
        pollutionService.start();

        try {
            pollutionService.join();
            JSONObject json = pollutionService.getJson();
            JSONObject data = json.getJSONObject("data");
            this.tvCity.setText(data.getString("city"));
            JSONObject current = data.getJSONObject("current");
            JSONObject pollution = current.getJSONObject("pollution");
            String aqius = pollution.getString("aqius");
            String mainus = pollution.getString("mainus");
            String aqicn = pollution.getString("aqicn");
            String maincn = pollution.getString("maincn");
            this.tvAqius.setText(String.format("%s (%s)", aqius, mainus));
            this.tvAqicn.setText(String.format("%s (%s)", aqicn, maincn));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
