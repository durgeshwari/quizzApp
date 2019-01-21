package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button mtrue;
    Button mfalse;
    TextView mQuestionTextView;
    int mQuestion;
    int mIndex=0;
    int mscore;
    ProgressBar mProgressBar;
    TextView mScore;

    // TODO: Uncomment to create question bank
    private trueFalse[] mQuestionBank = new trueFalse[] {
            new trueFalse(R.string.question_1, true),
            new trueFalse(R.string.question_2, true),
            new trueFalse(R.string.question_3, true),
            new trueFalse(R.string.question_4, true),
            new trueFalse(R.string.question_5, true),
            new trueFalse(R.string.question_6, false),
            new trueFalse(R.string.question_7, true),
            new trueFalse(R.string.question_8, false),
            new trueFalse(R.string.question_9, true),
            new trueFalse(R.string.question_10, true),
            new trueFalse(R.string.question_11, false),
            new trueFalse(R.string.question_12, false),
            new trueFalse(R.string.question_13,true)
    };
    final int PROGRESS_BAR_INCREMENT=(int)Math.ceil(100/ mQuestionBank.length);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            mscore=savedInstanceState.getInt("score key");
            mIndex=savedInstanceState.getInt("index key");
        }
        else{
            mscore=0;
            mIndex=0;
        }


        mtrue=(Button)findViewById(R.id.true_button);
        mfalse=(Button)findViewById((R.id.false_button));
        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);
        mScore=(TextView)findViewById(R.id.score);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        mQuestion=mQuestionBank[mIndex].getMquestionId();
        mQuestionTextView.setText(mQuestion);

        mScore.setText("score"+mscore+"/"+mQuestionBank.length);



        mtrue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();
                mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

            }});
        mfalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });

    }
    private void updateQuestion() {
        mIndex = (mIndex + 1) % mQuestionBank.length;
        mQuestion=mQuestionBank[mIndex].getMquestionId();
        mQuestionTextView.setText(mQuestion);
        mScore.setText("score"+mscore+"/"+mQuestionBank.length);
if(mIndex==0){
    AlertDialog.Builder alert =new AlertDialog.Builder(this);
    alert.setTitle("quiz over");
    alert.setCancelable(false);
    alert.setMessage("you scored"+mscore+"points");
    alert.setPositiveButton("close Application", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            finish();
        }
    });
    alert.show();
}

    }
    private void checkAnswer(boolean userSelection){
        boolean correctAnswer=mQuestionBank[mIndex].isManswerId();
        if (userSelection==correctAnswer){
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            mscore=mscore+1;
        }
        else
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
    }

    public void onSaveInstance(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("score key",mscore);
        outState.putInt("Index key",mIndex);

    }
}
