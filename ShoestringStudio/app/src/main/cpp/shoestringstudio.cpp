// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("shoestringstudio");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("shoestringstudio")
//      }
//    }
#include <thread>
#include <cinttypes>

#include "AudioEngine.h"
#include "AAsetDataSource.h"
#include <android/asset_manager.h>

void AudioEngine::start() {

    //Create a builders to open the audio streams
    oboe::AudioStreamBuilder sbOut;

    //Set the stream's callback to this object
    sbOut.setCallback(this);

    //Set misc stream properties
    sbOut.setDirection(oboe::Direction::Output);
    sbOut.setAudioApi(oboe::AudioApi::AAudio);
    sbOut.setChannelCount(1);

    //Open Audio Output Stream
    oboe::AudioStream *outStream = nullptr;
    oboe::Result result = sbOut.openStream(&outStream);

    //Check to see if the stream has been opened without any problems.
    if (result != oboe::Result::OK)
    {
        //LOG ERROR HERE
    }

    auto setBufferSizeResult = outStream->setBufferSizeInFrames(outStream->getFramesPerBurst()*2);

    //Request stream start
    result = outStream->requestStart();

    //Verify
    if (result != oboe::Result::OK)
    {
        //LOG ERROR HERE
    }
}

void AudioEngine:: setUpAudioSources()
{
    //std::shared_ptr<AAssetDataSource> backingTrackSource {
        //AAssetDataSource::newFromCompressedAsset(mAssetManager, "FUNKY_HOUSE.mp3", targetProperties)
    //}
}

//
oboe::DataCallbackResult AudioEngine::onAudioReady(oboe::AudioStream *oboeStream, void *audioData, int32_t numFrames)
{
    //Render audio streams
    //...
    return oboe::DataCallbackResult::Continue;
}
