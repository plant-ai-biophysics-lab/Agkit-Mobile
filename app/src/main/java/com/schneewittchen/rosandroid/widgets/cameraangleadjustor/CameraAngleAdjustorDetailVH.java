package com.schneewittchen.rosandroid.widgets.cameraangleadjustor;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.fragments.details.WidgetChangeListener;
import com.schneewittchen.rosandroid.ui.views.BaseDetailViewHolder;
import com.schneewittchen.rosandroid.utility.Utils;

/**
 * TODO: Description
 *
 * @author Neil Katahira
 * @version 1.0.0
 * @created on 02.11.2021
 */


import androidx.annotation.NonNull;

public class CameraAngleAdjustorDetailVH extends BaseDetailViewHolder<CameraAngleAdjustorEntity> {
    private EditText topicNameText;
    private EditText topicTypeEditText;

//    private EditText textText;
    private Spinner rotationSpinner;

    private ArrayAdapter<CharSequence> rotationAdapter;

    public CameraAngleAdjustorDetailVH(@NonNull View view, WidgetChangeListener updateListener) {
        super(view, updateListener);
    }

    @Override
    public void initView(View view) {
        topicNameText = view.findViewById(R.id.topicNameEditText);
        topicTypeEditText = view.findViewById(R.id.topicTypeEditText);

//        textText = view.findViewById(R.id.btnTextTypeText);
        rotationSpinner = view.findViewById(R.id.btnTextRotation);

        // Init spinner
        rotationAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.rotation, android.R.layout.simple_spinner_dropdown_item);

        rotationSpinner.setAdapter(rotationAdapter);
    }

    @Override
    protected void bindEntity(CameraAngleAdjustorEntity entity) {
        topicNameText.setText(entity.topic.name);
        topicTypeEditText.setText(entity.topic.type);

//        textText.setText(entity.text);
        rotationSpinner.setSelection(rotationAdapter.getPosition(Utils.numberToDegrees(entity.rotation)));
    }

    @Override
    public void updateEntity() {
        entity.topic.type = std_msgs.Float32._TYPE;
        entity.topic.name = topicNameText.getText().toString();

//        entity.text = textText.getText().toString();
        entity.rotation = Utils.degreesToNumber(rotationSpinner.getSelectedItem().toString());
    }
}
