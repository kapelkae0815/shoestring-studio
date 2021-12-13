//
// Created by JacobsWilliam on 12/1/2021.
//

#ifndef SHOESTRING_AUDIOENGINE_H
#define SHOESTRING_AUDIOENGINE_H

#include <oboe/Oboe.h>

class AudioEngine : public oboe::AudioStreamCallback {
public:
    //Start the audio stream(s)
    void start();

    //Process
    oboe::DataCallbackResult onAudioReady(oboe::AudioStream *oboeStream, void *audioData, int32_t numFrames);

    void setUpAudioSources();
};

#endif