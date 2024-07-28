import 'sample_plugin_flutter_platform_interface.dart';

class SamplePluginFlutter {
  Future<String?> getPlatformVersion() {
    return SamplePluginFlutterPlatform.instance.getPlatformVersion();
  }

  Future<String?> getSomething() {
    return SamplePluginFlutterPlatform.instance.getSomethingVersion();
  }

  Future<bool> UninstallApp(String package) {
    return SamplePluginFlutterPlatform.instance.UninstallApp(package);
  }
}


//
