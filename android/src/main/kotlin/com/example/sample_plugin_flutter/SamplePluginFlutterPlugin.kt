package com.example.sample_plugin_flutter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry

/** SamplePluginFlutterPlugin */
class SamplePluginFlutterPlugin: FlutterPlugin, MethodCallHandler, PluginRegistry.ActivityResultListener, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private var activity:Activity ?= null
  private var uninstallResult:Result?= null
  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "sample_plugin_flutter")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if(call.method == "Uninstall"){
      this.uninstallResult = result

      val intent = Intent(Intent.ACTION_DELETE)
      val app: String?= call.argument("package")
      intent.setData(Uri.parse("package:" + app))
      intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
      intent.putExtra(Intent.EXTRA_RETURN_RESULT, true)
      activity?.startActivityForResult(intent,1)

    }
//    if(call.method == "getSomething"){
//      result.success("Something work on Android..")
//    }
//    if (call.method == "getPlatformVersion") {
//      result.success("Android ${android.os.Build.VERSION.RELEASE}")
//    }
    else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onActivityResult(p0: Int, p1: Int, p2: Intent?): Boolean {
    if(p2 == null){
      uninstallResult?.success(false)
    }else{
      uninstallResult?.success(true)
    }
    return true
  }

  override fun onAttachedToActivity(p0: ActivityPluginBinding) {
    activity = p0.activity
    p0.addActivityResultListener(this)
  }

  override fun onDetachedFromActivityForConfigChanges() {

  }

  override fun onReattachedToActivityForConfigChanges(p0: ActivityPluginBinding) {

  }

  override fun onDetachedFromActivity() {

  }
}
