package com.lyw.greendaotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lyw.greendaotest.bean.User;
import com.lyw.greendaotest.manager.DaoManager;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edID;
    private EditText edName;
    private TextView tvResult;
    private UserDao mUserdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mUserdao = DaoManager.getInstance().getDaoSession().getUserDao();
    }

    private void initView() {
        edID = findViewById(R.id.editText2);
        edName = findViewById(R.id.editText);
        tvResult = findViewById(R.id.textView2);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                queryAll();
                //查
                break;
            case R.id.button2:
                delete();
                //删
                break;
            case R.id.button:
                add();
                //增
                break;
            case R.id.button3:
                change();
                //改
                break;
        }
    }

    private void add() {
        User user = new User();
        user.setName(edName.getText().toString());
        user.setHorseName(edName.getText().toString());
        mUserdao.insert(user);
    }

    private void change(){
        User user = query(edName.getText().toString());
        if(user == null){
            Toast.makeText(this,"无此数据",Toast.LENGTH_SHORT).show();
        }else {
            user.setName(edID.getText().toString());
            mUserdao.update(user);
            Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show();
        }

    }
        private  void delete(){
        User user = query(edName.getText().toString());
        if(user != null){
            mUserdao.delete(user);
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"无此数据",Toast.LENGTH_SHORT).show();
        }
    }

    private User query(String username) {
        User user = null;
        QueryBuilder<User> qb = mUserdao.queryBuilder();
        qb.where(UserDao.Properties.Name.eq(username));
        List<User> users = qb.list();
        if(users != null && users.size() > 0){
            user = users.get(0);
        }
        return user;
    }

    private void queryAll() {
        QueryBuilder<User> qb = mUserdao.queryBuilder();
        List<User> users = qb.list();
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(user.toString() + "\n");
        }
        tvResult.setText(sb);
    }
}
