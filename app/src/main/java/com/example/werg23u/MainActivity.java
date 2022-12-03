package com.example.werg23u;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ClassAdapter classAdapter;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fab;
    ArrayList<ClassItem> classItems=new ArrayList<>();
DbHelper dbHelper ;
Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab_main);
        fab.setOnClickListener(v -> showDialog());

        recyclerView=findViewById(R.id.RecyclerView);
        dbHelper=new DbHelper(this);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);

        myData();

        recyclerView.setLayoutManager(layoutManager);
        classAdapter=new ClassAdapter(this,classItems);
        recyclerView.setAdapter(classAdapter);
         classAdapter.setOnitemClicklistener(position -> gotoItemActivity(position));
        setToolbar();
    }

//    private void loadData() {
////        Cursor cursor=dbHelper.getClassTable();
//        Cursor cursor=dbHelper.getClassTable();
//      classItems.clear();
//      while (cursor.moveToNext()){
//      int id=cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.C_ID));
//    String className = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.CLASS_NAME_KEY));
//    String subjectName = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.SUBJECT_NAME_KEY));
//    classItems.add(new ClassItem( id,className,subjectName));
//    }

    private void myData() {
        Cursor cursor=dbHelper.getClassTable();
        classItems.clear();
while (cursor.moveToNext()){
    int id=cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.C_ID));
    String className = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.CLASS_NAME_KEY));
    String subjectName = cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.SUBJECT_NAME_KEY));
    classItems.add(new ClassItem( id,className,subjectName));

}
    }

    private void setToolbar() {

        toolbar=findViewById(R.id.toolbar);
        TextView title_tl=findViewById(R.id.titlr_toolbar);
        TextView subTitle=findViewById(R.id.subtitl_toolbar);
        ImageButton backbtn=findViewById(R.id.back);
        ImageButton savebtn=findViewById(R.id.save);

        title_tl.setText("Attendance App");
        subTitle .setVisibility(View.GONE);
        backbtn.setVisibility(View.INVISIBLE);
        savebtn.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActivity(int position) {
        Intent i = new Intent(this, studentActivity.class);
        i.putExtra("className",classItems.get(position).getClassName());
        i.putExtra("subjectName",classItems.get(position).getSubjectName());
        i.putExtra("postion",position);
        startActivity(i);

    }

//    private void showDialog() {
//
////        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//////        View view= LayoutInflater.from((this).inflate(R.layout.class_dialog,null);
////        View view=LayoutInflater.from(this).inflate(R.layout.dialog,null);
////        builder.setView(view);
////       AlertDialog dialog= builder.show();
////       dialog.show();
////
////    class_edt=view.findViewById(R.id.edt01);
////    subject_edt=view.findViewById(R.id.edt02);
////
////        Button cancel=view.findViewById(R.id.cancel_btn);
////        Button add=view.findViewById(R.id.add_btn);
////        cancel.setOnClickListener(v -> dialog.dismiss() );
////        add.setOnClickListener(v -> {
////            addclass();
////            dialog.dismiss();
////
////        });
//MyDialog dialog=new MyDialog();
////dialog.show(getSupportFragmentManager(),MyDialog.CLASS_ADD_DIALOG);

//
//   //next
//    dialog.show(getSupportFragmentManager(),MyDialog.CLASS_ADD_DIALOG);
//        dialog.setListener((ClassName,subName)->addclass(ClassName,subName));
//
//    }
private void showDialog() {
    MyDialog dialog = new MyDialog();
    dialog.show(getSupportFragmentManager(),MyDialog.CLASS_ADD_DIALOG);
    dialog.setListener(( ClassName,subjectName)->addClass(ClassName,subjectName));

}

    private Void addClass(String className, String subjectName) {
        long cid= dbHelper.addclass(className,subjectName);
        ClassItem classItem=new ClassItem(className,subjectName);
        classItems.add (classItem);
        classAdapter.notifyDataSetChanged();


        return null;
    }
}