package sz.ultima.imageu.viewz;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ScrollView;

/**
 * 阻尼回弹
 *只需在布局文件中把该控件嵌入其中即可，用法同ScrollView
 *
 *可以使用nineoldandroid.jar中的ObjectAnimator，以提高低版本的兼容性
 * @author wenjundu 2015-7-31
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class OverScrollView extends  ScrollView{

	private boolean ispulldown=true;
        private Context context;
        public OverScrollView(Context context, AttributeSet attrs, int defStyle) {
                
                super(context, attrs, defStyle);
                this.context=context;
                // TODO Auto-generated constructor stub
        }
        public OverScrollView(Context context, AttributeSet attrs  ) {
                super(context, attrs );
                this.context=context;
                // TODO Auto-generated constructor stub
        }
        public OverScrollView(Context context ) {
                super(context );
                this.context=context;
                // TODO Auto-generated constructor stub
        }

        private OnOverScrollListener listener;
        public void setOnOverScrollListener(OnOverScrollListener listener) {
                this.listener=listener;
        }
         
        public interface OnOverScrollListener {
                void overScrollDown(float dis);
                void overScrollUp(float dis);
                void onScrollResumeFromBottomToTop(float maxDis, float dis);
                void onScrollResumeFromTopToBottom(float maxDis, float dis);
                void onScrollResumeFinished();
                
        }
        private float pressY;
        private float downDis;
        private float upDis;
        
        private float pressX;
        //设置是否可以下拉
         public void setIsPullDown(boolean ispulldown){
        	 this.ispulldown=ispulldown;
         }
        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
                // TODO Auto-generated method stub
                
                if (ev.getAction()==MotionEvent.ACTION_DOWN) {
                        pressY=ev.getRawY();
                        pressX=ev.getRawX();
//                        System.out.println("onInterceptTouchEvent down "+pressY);
                }
                if (ev.getAction()==MotionEvent.ACTION_MOVE) {
                        float nowX=ev.getRawX();
                        float nowY=ev.getRawY();
                        
                        float disX=Math.abs(nowX-pressX);
                        float disY=Math.abs(nowY-pressY);
                        if (disY>=ViewConfiguration.get(context).getScaledTouchSlop()&&disY>disX) {
                                return true;
                        }
                        
                }
                return super.onInterceptTouchEvent(ev);
        }

		@Override
        public boolean onTouchEvent(final MotionEvent ev) {
                // TODO Auto-generated method stub
                switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                        pressY=ev.getRawY();
//                        System.out.println("down "+pressY);
                        break;
                case MotionEvent.ACTION_MOVE:
                        int sy=getScrollY();
//                        System.out.println("move    getRawY�� " +ev.getRawY()+   "    getScrollY: "+sy  +"   getChildAt(0).getHeight()��"+getChildAt(0).getHeight()+"   getHigh():"+getHeight());
                        if (sy==0&&ev.getRawY()>pressY) {
//                                isDown=true;
                        	if(ispulldown){
                                downDis=(float) ((ev.getRawY()-pressY)/3);
                                getChildAt(0).setTranslationY(downDis);
                                if (listener!=null) {
                                        listener.overScrollDown(downDis);
                                }
                        	}
                                return true;
                        }
//                        if (isDown) {
//                                
//                                setTranslationY(ev.getRawY()-pressY);
//                                
//                                return true;
//                        }
                         
                        if (sy+getHeight()==getChildAt(0).getHeight()&&ev.getRawY()<pressY) {
//                                isUp=true;
                                upDis=(float) ((ev.getRawY()-pressY)/3);
                                getChildAt(0).setTranslationY(upDis);
                                if (listener!=null) {
                                        listener.overScrollUp(upDis);
                                }
                                return true;
                                
                        }
                        
                        if (getHeight()>=getChildAt(0).getHeight()&&ev.getRawY()<pressY) {
                                upDis=(float) ((ev.getRawY()-pressY)/3);
                                getChildAt(0).setTranslationY(upDis);
                                if (listener!=null) {
                                        listener.overScrollUp(upDis);
                                }
                                return true;
                        }
//                        if (isUp) {
//                                setTranslationY(ev.getRawY()-pressY);
//                                return true;
//                                
//                        }
                        pressY=ev.getRawY();
                        try {
                                return super.onTouchEvent(ev);
                        } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                return false;
                        }
                         
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: 
                        final float start=getChildAt(0).getTranslationY();
                        ObjectAnimator animator=ObjectAnimator.ofFloat(getChildAt(0), "translationY", start,0);
                       // animator.setDuration(500);
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.addUpdateListener(new AnimatorUpdateListener() {
                                
                                @SuppressLint("NewApi")
								@Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                        // TODO Auto-generated method stub
                                        if (listener!=null) {
                                                if (ev.getRawY()>pressY) {
                                                        listener.onScrollResumeFromBottomToTop(start,(Float)animation.getAnimatedValue());
                                                }else
                                                        listener.onScrollResumeFromTopToBottom(start,(Float)animation.getAnimatedValue());
                                        }
                                }
                        });
                        
                        animator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                        // TODO Auto-generated method stub
                                        super.onAnimationEnd(animation);
                                        if (listener!=null) {
                                                listener.onScrollResumeFinished();
                                        }
                                }
                        });
                        animator.start();
                        
                        break;

                 
                }
                return true;
        }
   
}
