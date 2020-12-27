package com.example.doan.GiaoDien;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doan.Adapter.DichVuAdapter;
import com.example.doan.Adapter.ListAnhAdapter;
import com.example.doan.DataBase.DBDichVu;
import com.example.doan.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class DichVu extends AppCompatActivity {
    EditText txtMaDV, txtTenDV, txtDonGia, txtSoLuong;
    ImageView imgHinhA;
    Button btnThem, btnXoa, btnSua, btnLamSach;
    ListView lvDichVu;
    final int REQUEST_CODE = 999;

    ArrayList<com.example.doan.Model.DichVu> data_DV = new ArrayList<>();
    DichVuAdapter adapter_DV;


    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_vu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        try {
            DBDichVu dbDichVu = new DBDichVu(this);
            data_DV = dbDichVu.LayDL();

            adapter_DV = new DichVuAdapter(this, R.layout.dichvu_adapter, data_DV);
            lvDichVu.setAdapter(adapter_DV);

            registerForContextMenu(lvDichVu);
        } catch (Exception e) {

        }
        imgHinhA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(DichVu.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBDichVu dbDichVu = new DBDichVu(getApplicationContext());
                    com.example.doan.Model.DichVu dichVu = getDichVu();
                    dbDichVu.them(dichVu);
                    Toast.makeText(getApplication(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    //update lai listview
                    data_DV.add(getDichVu());
                    adapter_DV.notifyDataSetChanged();
                    finish();
                    Intent intent = new Intent(DichVu.this,MainActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        lvDichVu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.example.doan.Model.DichVu dichVu = data_DV.get(position);
                txtMaDV.setText(dichVu.getMaDV());
                txtTenDV.setText(dichVu.getTenDV());
                txtDonGia.setText(dichVu.getDonGia() + "");
                txtSoLuong.setText(dichVu.getSoLuong() + "");
                byte[] hinhAnh = dichVu.getHinhAnh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                imgHinhA.setImageBitmap(bitmap);

                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBDichVu dbDichVu = new DBDichVu(getApplication());
                com.example.doan.Model.DichVu dichVu = getDichVu();
                dbDichVu.xoa(dichVu);

                data_DV.remove(index);
                adapter_DV.notifyDataSetChanged();
                finish();

                Intent intent = new Intent(DichVu.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBDichVu dbDichVu = new DBDichVu(getApplication());
                com.example.doan.Model.DichVu dichVu = getDichVu();
                dbDichVu.sua(dichVu);

                data_DV.set(index, dichVu);
                adapter_DV.notifyDataSetChanged();
                finish();

                Intent intent = new Intent(DichVu.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private com.example.doan.Model.DichVu getDichVu() {
        com.example.doan.Model.DichVu dichVu = new com.example.doan.Model.DichVu();
        dichVu.setTenDV(txtTenDV.getText().toString());
        dichVu.setMaDV(txtMaDV.getText().toString());
        dichVu.setDonGia(Integer.parseInt(txtDonGia.getText().toString()));
        dichVu.setSoLuong(Integer.parseInt(txtSoLuong.getText().toString()));
        dichVu.setHinhAnh(imageViewToByte(imgHinhA));

        return dichVu;
    }

    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Toast.makeText(this, "Chưa cấp quyền truy cập!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinhA.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 1 && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinhA.setImageBitmap(bitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setControl() {
        txtMaDV = findViewById(R.id.txtMaDV);
        txtTenDV = findViewById(R.id.txtTenDV);
        txtDonGia = findViewById(R.id.txtDonGia);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        imgHinhA = findViewById(R.id.imgHinhA);

        btnThem = findViewById(R.id.btnThemDV);
        btnXoa = findViewById(R.id.btnXoaDV);
        btnSua = findViewById(R.id.btnSuaDV);
        btnLamSach = findViewById(R.id.btnLamSachDV);

        lvDichVu = findViewById(R.id.lvDichVu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.mnChupAnh:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
                break;
            case R.id.mnList:
                DialogHinhAnh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void DialogHinhAnh(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_hinhanh);
        //ánh xạ
        ListView lvLuaChonHinhAnh = dialog.findViewById(R.id.lvLuaChonHinhAnh);
        Button btnThoatDialog = dialog.findViewById(R.id.btnThoatDialog);
        ArrayAdapter adapterLuaChonAnh;
        adapterLuaChonAnh = new ListAnhAdapter(this, R.layout.listanh_adapter, data_DV);
        lvLuaChonHinhAnh.setAdapter(adapterLuaChonAnh);
        //them adapter hinh anh
        dialog.show();
        btnThoatDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        lvLuaChonHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.example.doan.Model.DichVu dichVu = data_DV.get(position);
                byte[] hinhAnh = dichVu.getHinhAnh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                imgHinhA.setImageBitmap(bitmap);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dichvu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
