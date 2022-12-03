package com.example.werg23u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class studentActivity extends AppCompatActivity {
Toolbar toolbar;
private String className;
private String subjectName;
private int Position;
private RecyclerView recyclerView;
private studentAdapter adapter;
private RecyclerView.LayoutManager layoutManager;
private ArrayList<studentitem> studentitems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent intent=getIntent();
        className=intent.getStringExtra("className");
        subjectName=intent.getStringExtra("subjectName");
        Position=intent.getIntExtra("position",-1);
        setToolbar();
        recyclerView=findViewById(R.id.student_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new studentAdapter(this,studentitems);
        recyclerView.setAdapter(adapter);
        adapter.setOnitemClicklistener(position -> changestatus(position));

    }

    private void changestatus(int position) {
        String status=studentitems.get(position).getStatus();

        if(status.equals("P")) status="A";
        else status="P";
        studentitems.get(position).setStatus(status);
        adapter.notifyItemChanged(position);
    }

    private void setToolbar() {

        toolbar=findViewById(R.id.toolbar);
        TextView title_tl=findViewById(R.id.titlr_toolbar);
        TextView subTitle=findViewById(R.id.subtitl_toolbar);
        ImageButton backbtn=findViewById(R.id.back);
        ImageButton savebtn=findViewById(R.id.save);
        title_tl.setText(className);
        subTitle.setText(subjectName);

         backbtn.setOnClickListener(v -> onBackPressed());
         toolbar.inflateMenu(R.menu.student_menu);
         toolbar.setOnMenuItemClickListener(menuItem->onMenuItemClick(menuItem));
//        title_tl.setText("Attendance App");
//        subTitle .setVisibility(View.GONE);
//        backbtn.setVisibility(View.INVISIBLE);
//        savebtn.setVisibility(View.INVISIBLE);
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        
        
        if(menuItem.getItemId()==R.id.add_student){
            showAddstudent();
        }
        return true;
    }
    private void showAddstudent() {
        MyDialog dialog=new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.STUDENT_ADD_DIALOG);
        dialog.setListener(( roll,name)->addstudent(roll,name));
    }
    private Void addstudent(String roll, String name) {
       studentitems.add(new studentitem(roll,name));
       adapter.notifyItemChanged( studentitems.size()-1);
       return null;
    }
}