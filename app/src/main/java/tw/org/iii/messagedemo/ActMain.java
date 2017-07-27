package tw.org.iii.messagedemo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ActMain extends AppCompatActivity {
    String[] customers={"Taiwan","China","Philip","Malaysia","Japan","USA","UK","Canada","Tokyo","1","2","3","4","5","6","7","8","9"};
    private DialogInterface.OnClickListener btnOK_click = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            lblMessage.setText("Hello");
        }
    };
    private View.OnClickListener btnBuilder_click = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            AlertDialog.Builder build=new AlertDialog.Builder(ActMain.this);
            build.setTitle("這是標題");
            build.setMessage("這是訊息內容");
            build.setPositiveButton("OK",null);
            Dialog message=build.create();
            message.show();

        }
    };
    private DialogInterface.OnClickListener btnReceiver_click = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            lblMessage.setText(customers[which]);
        }
    };
    private View.OnClickListener btnList_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            AlertDialog.Builder build=new AlertDialog.Builder(ActMain.this);
            build.setTitle("請選擇收件人");
            build.setItems(customers,btnReceiver_click);
            build.setPositiveButton("OK",btnOK_click);
            Dialog message=build.create();
            message.show();
        }
    };
    DatePickerDialog.OnDateSetListener btnDateSetting_Click = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            lblMessage.setText(String.valueOf(year)+"/"+
                    String.valueOf(month+1)+"/"+
                    String.valueOf(dayOfMonth));

            }
    };
    private View.OnClickListener btnDate_click = new View.OnClickListener(){


        @Override
        public void onClick(View v) {

            Calendar today= Calendar.getInstance();
            Dialog message=new DatePickerDialog(
                    ActMain.this,
                    btnDateSetting_Click,
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH),
                    today.get(Calendar.DATE)
            );
            message.show();
        }
    };
    private View.OnClickListener btnToast_click=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Toast message= Toast.makeText(
              ActMain.this,
                    "Testing this wifi msg",
                    Toast.LENGTH_LONG
            );
            message.show();
        }
    };
    private View.OnClickListener btnSnackAction_click = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            lblMessage.setText("Hello snackbar");

        }
    };
    private View.OnClickListener btnSnackbar_click = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Snackbar.make(v,"偵測到 wifi 訊號",Snackbar.LENGTH_LONG)
                    .setAction("OK",btnSnackAction_click)
                    .setActionTextColor(Color.YELLOW)
                    .show();
        }
    };
    private View.OnClickListener btnNotification_click=new View.OnClickListener(){

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            Notification message = new Notification.Builder(ActMain.this)
                    .setContentTitle("您有 3 封簡訊未讀取")
                    .setContentText("簡訊來自 Winnie")
                    .setSmallIcon(android.R.drawable.stat_sys_speakerphone)
                    .build();
            NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.notify(0, message);
        }
    };
    private View.OnClickListener btnProgress_click=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            ProgressDialog l_dialog=new ProgressDialog(ActMain.this);
            l_dialog.setMax(100);
            l_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            l_dialog.setTitle("正在讀取資料");
            l_dialog.setCancelable(false);
            l_dialog.incrementProgressBy(26);
            l_dialog.incrementProgressBy(28);
            l_dialog.show();
        }
    };

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==0)
            lblMessage.setText("copy");
        else if(item.getItemId()==1)
            lblMessage.setText("post");
        else if(item.getItemId()==2)
            lblMessage.setText("cut");

        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,1,"copy");
        menu.add(0,1,2,"Post");
        menu.add(0,2,3,"cut");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        InitialComponent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==0)
            lblMessage.setText("copy");
        else if(item.getItemId()==1)
            lblMessage.setText("post");
        else if(item.getItemId()==2)
            lblMessage.setText("cut");

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,1,"copy");
        menu.add(0,1,2,"Post");
        menu.add(0,2,3,"cut");
        return super.onCreateOptionsMenu(menu);
    }

    private void InitialComponent() {
        btnBuilder=(Button)findViewById(R.id.btnBuilder);
        btnBuilder.setOnClickListener(btnBuilder_click);
        btnList=(Button)findViewById(R.id.btnList);
        btnList.setOnClickListener(btnList_click);
        lblMessage=(TextView)findViewById(R.id.lblMessage);
        btnDate=(Button)findViewById(R.id.btnSend);
        btnDate.setOnClickListener(btnDate_click);
        btnProgress=(Button)findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(btnProgress_click);
        btnToast=(Button)findViewById(R.id.btnToast);
        btnToast.setOnClickListener(btnToast_click);
        btnSnackbar=(Button)findViewById(R.id.btnSnackbar);
        btnSnackbar.setOnClickListener(btnSnackbar_click);
        btnNotification=(Button)findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(btnNotification_click);
        registerForContextMenu(lblMessage);
    }
    Button btnBuilder;
    Button btnList;
    Button btnProgress;
    Button btnToast;
    Button btnSnackbar;
    Button btnDate;
    Button btnNotification;
    TextView lblMessage;
}
