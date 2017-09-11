package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreButton;
    private Button mCheatButton;   //cheat作弊按钮
    private TextView mQuestionTextView;
    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX= "index";
    //调用StartActivityForResult()方法
    private static final int REQUEST_CODE_CHEAT=0;


    private Question[] mQuestionBank=new Question[]{
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),//实例化一个数组，匿名类
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };

    private int mCurrentIndex= 0; //Question 的index
    private boolean mIsCheater;


    private void updateQuestion(){  //增加updateQuestion()方法
        int question  = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);//拿出一个字符串

    }
    private  void checkAnswer(boolean userPressedTrue){     //检查用户答案是否正确
        boolean answerIsTrue =mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if(mIsCheater){
            messageResId=R.string.judgment_toast;

        }else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;

            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) called");      //不明白，添加日志输出代码
        setContentView(R.layout.activity_quiz);

        mQuestionTextView =(TextView) findViewById(R.id.question_text_view);

        //public static Toast makeText(Context context,int resId,int duration);

        mTrueButton=(Button) findViewById(R.id.true_button);//为正确按钮设置监听
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /* Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();*/
            checkAnswer(true);

            }
        });
        mFalseButton=(Button) findViewById(R.id.false_button);//为错误按钮设置监听
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();*/
               checkAnswer(true);
            }
        });

        //返回上一题
        mPreButton=(ImageButton) findViewById(R.id.pre_button);
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
               if(mCurrentIndex == 0) {
                   Toast.makeText(QuizActivity.this,"这是第一个哦，没有前一个啦!",Toast.LENGTH_SHORT).show();
                }
                mIsCheater=false;
                updateQuestion();

            }
        });
        mNextButton=(ImageButton)findViewById(R.id.next_button);//为next按钮设置监听
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
             /*  int question=mQuestionBank[mCurrentIndex].getmTextResId();
                mQuestionTextView.setText(question);*/

                 mIsCheater=false;
                 updateQuestion();             //调用updateQuestion()方法，拿出问题
            }
        });

        mCheatButton=(Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {//设置监听
            @Override
            public void onClick(View view) {
               /* Intent intent=new Intent(QuizActivity.this, CheatActivity.class);
              */
               boolean answerIsTrue =mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this,answerIsTrue);

              /* startActivity(i);*/
             startActivityForResult(intent,REQUEST_CODE_CHEAT);
            }
        });
        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion();


    }

    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode== REQUEST_CODE_CHEAT){
            if(data==null){
                return;
            }
            mIsCheater=CheatActivity.wasAnswerShown(data);

        }

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);

    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");

    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");

    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");

    }
/*  public static void main(String[] args) {
        System.out.println();

    }*/

}

