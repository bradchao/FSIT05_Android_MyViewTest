package tw.brad.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

public class MyView extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private GestureDetector gd;
    private Paint paint = new Paint();


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);

        lines = new LinkedList<>();

        gd = new GestureDetector(context, new MyGestureListener());

        setBackgroundColor(Color.YELLOW);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.v("brad", "onDown");
            return true;    //super.onDown(e);
        }


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float vX, float vY) {
            if (Math.abs(vX) > Math.abs(vY) + 1000){
                if (vX > 0){
                    Log.v("brad", "Right");
                }else{
                    Log.v("brad", "Left");
                }
            }else if (Math.abs(vY) > Math.abs(vX) + 1000){
                if (vY > 0){
                    Log.v("brad", "Down");
                }else{
                    Log.v("brad", "Up");
                }
            }

            return super.onFling(e1, e2, vX, vY);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        for (LinkedList<HashMap<String,Float>> line : lines) {
            for (int i = 1; i < line.size(); i++) {
                HashMap<String, Float> p0 = line.get(i - 1);
                HashMap<String, Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"), p0.get("y"),
                        p1.get("x"), p1.get("y"), paint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        float ex = event.getX();
//        float ey = event.getY();
//        HashMap<String,Float> point = new HashMap<>();
//        point.put("x", ex); point.put("y", ey);
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                LinkedList<HashMap<String,Float>> line = new LinkedList<>();
//                line.add(point);
//                lines.add(line);
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                lines.getLast().add(point);
//                break;
//        }
//        invalidate();
//
//        return true; //super.onTouchEvent(event);


        return gd.onTouchEvent(event);

    }

    public void clear(){
        lines.clear();
        invalidate();
    }

}
