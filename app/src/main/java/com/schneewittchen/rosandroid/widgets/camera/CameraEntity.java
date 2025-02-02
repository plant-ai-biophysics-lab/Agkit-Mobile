package com.schneewittchen.rosandroid.widgets.camera;

import com.schneewittchen.rosandroid.model.entities.SubscriberEntity;
import com.schneewittchen.rosandroid.model.repositories.rosRepo.message.Topic;

import sensor_msgs.Image;


/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 27.04.2020
 * @updated on 27.10.2020
 * @modified by Nico Studt
 */
public class CameraEntity extends SubscriberEntity {

    int colorScheme;
    boolean drawBehind;
    boolean useTimeStamp;
    public int rotation;


    public CameraEntity() {
        this.width = 5;
        this.height = 4;
        this.topic = new Topic("camera/image_compressed", Image._TYPE);
        this.rotation = 0;
    }
}

