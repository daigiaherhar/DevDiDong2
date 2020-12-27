package com.example.doan.GiaoDien.Fragment;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doan.DataBase.DBKhachHang;
import com.example.doan.DataBase.DBPhatSinh;

import com.example.doan.GiaoDien.TaoPhieu_MuaHang;

import com.example.doan.Model.PhatSinh;
import com.example.doan.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ManHinhChinh extends Fragment {
    Button btnThem,btnTimKiemKH;
    ListView listNavigation, lvDichVuHight;
    TextView txtMaKH, txtTenKH, txtNgaySinh, txtDiaChi;

    ArrayList<com.example.doan.Model.DichVu> arrDV = new ArrayList<>();
    ArrayList<com.example.doan.Model.PhatSinh> arrPS = new ArrayList<>();
    ArrayList<com.example.doan.Model.KhachHang> arrKH = new ArrayList<>();
    ArrayAdapter adapterDV;

    int loi = 0;
    public static int soPhieuPhatSinh = 0;
    public static String maKH = "";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate((R.layout.man_hinh_chinh), container, false);

        try{
            btnThem = rootView.findViewById(R.id.btnThem);
            btnTimKiemKH = rootView.findViewById(R.id.btnTimKiemKH);

            txtMaKH = rootView.findViewById(R.id.txtMaKH);
            txtDiaChi = rootView.findViewById(R.id.txtDiaChi);
            txtNgaySinh = rootView.findViewById(R.id.txtNgaySinh);
            txtTenKH = rootView.findViewById(R.id.txtTenKH);
            setEvent();

        }catch (Exception e){

        }
        return rootView;
    }
    private void setEvent() {


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//                String date1 = df.format(Calendar.getInstance().getTime());
//                Toast.makeText(getApplication(), date1,Toast.LENGTH_SHORT).show();
                if(txtMaKH.getText().length() == 0)
                {
                    Toast.makeText(getActivity(), "Nhập mã khách hàng!", Toast.LENGTH_SHORT).show();
                }
                else if(loi == 0) {
                    try {
                        DBKhachHang dbKhachHang = new DBKhachHang(getActivity());
                        String key = txtMaKH.getText() + "";
                        arrKH = dbKhachHang.TimKiemMa(txtMaKH.getText() + "");

                        loi = 1;
                    } catch (Exception e) {

                        Toast.makeText(getActivity(), "Không tìm thấy!", Toast.LENGTH_SHORT).show();
                    }
                }
                if(loi == 1)
                {
                    loi = 0;
                    DBPhatSinh dbPhatSinh = new DBPhatSinh(getActivity());
                    PhatSinh phatSinh = getPhatSinh();
                    dbPhatSinh.them(phatSinh);

                    //create public soPhieu send layout_TaoPhieu_MuaHang
                    arrPS = dbPhatSinh.LayDL();
                    soPhieuPhatSinh = arrPS.size();
                    maKH = txtMaKH.getText().toString();
                    Intent intent = new Intent(getActivity(), TaoPhieu_MuaHang.class);
                    startActivity(intent);
                }

            }
        });
        btnTimKiemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtMaKH.getText().length() == 0) {
                    return;
                } else {

                    try {
                        DBKhachHang dbKhachHang = new DBKhachHang(getActivity());
                        String key = txtMaKH.getText() + "";
                        arrKH = dbKhachHang.TimKiemMa(txtMaKH.getText() + "");

                        Toast.makeText(getActivity(), key, Toast.LENGTH_SHORT).show();
                        txtTenKH.setText(arrKH.get(0).getTen());
                        txtNgaySinh.setText(arrKH.get(0).getNgaySinh());
                        txtDiaChi.setText(arrKH.get(0).getDiaChi());

                    } catch (Exception e) {

                        Toast.makeText(getActivity(), "Không tìm thấy!", Toast.LENGTH_SHORT).show();
                    }
//
                }
            }
        });


    }
    private PhatSinh getPhatSinh() {
        PhatSinh phatSinh = new PhatSinh();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        phatSinh.setNgayLap(date);


        phatSinh.setMaKH(txtMaKH.getText().toString());
        return phatSinh;
    }

}
