package be.howest.nmct.colorpicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Nikita on 27/02/2015.
 */
public class ColorView  extends View{
    private String color = "#FFFFFF";
    private Paint paint;
    private Rect rect;

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rect = new Rect(0,0,getWidth(), getHeight());
        paint = new Paint();
        paint.setColor(Color.parseColor(color));

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorDialog();
            }
        });


    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect.set(0,0,getWidth(),getHeight());
        canvas.drawRect(rect,paint);
    }

    protected  void showColorDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Pick a color")
                .setItems(R.array.holo_colors, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        selectColor(which);
                    }
                });
        builder.create().show();
    }
    protected void selectColor(int which){
        switch (which){
            case 0:
                setColor("#33B5E5");
                break;
            case 1:
                setColor("#AA66CC");
                break;
            case 2:
                setColor("#99CC00");
                break;
            case 3:
                setColor("#FFBB33");
                break;
            case 4:
                setColor("#FF4444");
                break;
            default:
                break;
        }
    }
}

