import 'package:flutter_test/flutter_test.dart';
import 'package:sample_plugin_flutter/sample_plugin_flutter.dart';
import 'package:sample_plugin_flutter/sample_plugin_flutter_platform_interface.dart';
import 'package:sample_plugin_flutter/sample_plugin_flutter_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockSamplePluginFlutterPlatform
    with MockPlatformInterfaceMixin
    implements SamplePluginFlutterPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final SamplePluginFlutterPlatform initialPlatform = SamplePluginFlutterPlatform.instance;

  test('$MethodChannelSamplePluginFlutter is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelSamplePluginFlutter>());
  });

  test('getPlatformVersion', () async {
    SamplePluginFlutter samplePluginFlutterPlugin = SamplePluginFlutter();
    MockSamplePluginFlutterPlatform fakePlatform = MockSamplePluginFlutterPlatform();
    SamplePluginFlutterPlatform.instance = fakePlatform;

    expect(await samplePluginFlutterPlugin.getPlatformVersion(), '42');
  });
}
