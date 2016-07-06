package tomerbu.edu.menusanddialogs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.Rating;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RelativeLayout layout;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = (RelativeLayout) findViewById(R.id.layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog();
               // showCustomDialog();
               // showBurgerToppings();
               // showProgressDialog();
               // showListDialog();
               // showImagePickerDialog();
            }
        });

    }

    private void showTimePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View v = getLayoutInflater().inflate(R.layout.time_picker_dialog, layout, false);

        builder.setView(v);

        final AlertDialog dialog = builder.show();

        final TimePicker picker = (TimePicker) v.findViewById(R.id.timePicker);
        Button btn = (Button) v.findViewById(R.id.btnSave);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, picker.getCurrentHour() + " Hours " + picker.getCurrentMinute() + " Minutes ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View dialogView = getLayoutInflater().
                inflate(R.layout.rateus_dialog, layout, false);

        builder.setView(dialogView);

        final AlertDialog dialog = builder.show();

        RatingBar rb = (RatingBar) dialogView.findViewById(R.id.rateus_bar);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, rating + " ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void showBurgerToppings() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.pick_toppings));
        final String[] toppings = getResources().getStringArray(R.array.toppings);
        final boolean[] defaults = new boolean[toppings.length];
        defaults[0] = true;
        defaults[1] = true;
        defaults[2] = true;

        builder.setMultiChoiceItems(toppings, defaults, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                defaults[which] = isChecked;
            }
        });

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                for (int i = 0; i < toppings.length; i++) {
                    Toast.makeText(MainActivity.this, toppings[i] + "  " + defaults[i], Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(this);

        dialog.setTitle("Logging you in...");
        dialog.setMessage("Please wait");

        dialog.setCancelable(false);
        dialog.show();
    }

    String selectedDay = "";

    private void showListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getString(R.string.choose_city));
        builder.setItems(R.array.days, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedDay = getResources().getStringArray(R.array.days)[which];
                Toast.makeText(MainActivity.this, selectedDay, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }

    int[] images = {R.drawable.back1, R.drawable.back2, R.drawable.back3, R.drawable.back4, R.drawable.back5};
    int currentIndex = 0;

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("בחר תמונה").setPositiveButton(">", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                layout.setBackgroundResource(images[currentIndex]);
                currentIndex++;
                if (currentIndex >= images.length) {
                    currentIndex = 0;
                }
            }
        }).setNegativeButton("<", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                layout.setBackgroundResource(images[currentIndex]);
                currentIndex--;
                if (currentIndex < 0) {
                    currentIndex = images.length - 1;
                }
            }
        }).show();
    }

    //create the options menu from the menu_main.xml:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //The On Click listener of the menu:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_change_background) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
