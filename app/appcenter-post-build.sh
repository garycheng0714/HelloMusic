#!/usr/bin/env bash
echo 'start UI test'
echo $APPCENTER_OUTPUT_DIRECTORY
echo $APPCENTER_SOURCE_DIRECTORY

cp $APPCENTER_SOURCE_DIRECTORY/app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk $APPCENTER_OUTPUT_DIRECTORY

appcenter test run espresso \
    --app "d9369604-gmail.com/Hello-Music" \
    --devices c9eaa83b \
    --app-path $APPCENTER_SOURCE_DIRECTORY/app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk  \
    --test-series "espresso-tests" \
    --locale "en_US" \
    --build-dir $APPCENTER_OUTPUT_DIRECTORY \
    --token $appCenterLoginApiToken



#appcenter test run uitest \
#    --app "tomso/Pickster" \
#    --devices "tomso/top-devices" \
#    --app-path $APPCENTER_OUTPUT_DIRECTORY/Pickster.ipa \
#    --test-series "smoke-tests" \
#    --locale "en_US" \
#    --build-dir $APPCENTER_SOURCE_DIRECTORY/Pickster.UITests/bin/Debug \
#    --uitest-tools-dir $APPCENTER_SOURCE_DIRECTORY/packages/Xamarin.UITest.*/tools \
#    --token $appCenterLoginApiToken
