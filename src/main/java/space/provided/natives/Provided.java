package space.provided.natives;

import space.provided.api.AssetItem;
import space.provided.api.ApiException;
import space.provided.api.ProvidedUser;
import space.provided.api.oauth2.MicrosoftAuthException;
import space.provided.api.oauth2.MicrosoftAuthResult;

public final class Provided {

    public static void loadAndInitialize(String payload, String signature) throws InitializationException {
        System.load("provided");
        initialize(payload, signature);
    }

    public static native void initialize(String payload, String signature) throws InitializationException;

    public static native void uninitialize();

    public static native ProvidedUser getUser() throws ApiException;

    public static native ProvidedUser redeemToken(String token) throws ApiException;

    public static native AssetItem[] getAssetItems(int assetId) throws ApiException;

    public static native String getAssetItemContents(int itemId) throws ApiException;

    public static native AssetItem uploadAssetItem(int assetId, String name, String contents) throws ApiException;

    public static native void deleteAssetItem(int itemId) throws ApiException;

    public static class Obfuscation {

        public static native String encrypt(String value, String key);

        public static native String decrypt(String value, String key);

        public static native int i_a(int n1, int n2);

        public static native int i_b(int n1, int n2);

        public static native int i_c(int n1, int n2);

        public static native int i_d(int n1, int n2);

        public static native int i_e(int n1, int n2);

        public static native int i_f(int n1, int n2);

        public static native int i_g(int n1, int n2);

        public static native int i_h(int n1, int n2);

        public static native int i_i(int n1, int n2);

        public static native int i_j(int n1, int n2);

        public static native long l_a(long n1, long n2);

        public static native long l_b(long n1, long n2);

        public static native long l_c(long n1, long n2);

        public static native long l_d(long n1, long n2);

        public static native long l_e(long n1, long n2);

        public static native long l_f(long n1, long n2);

        public static native long l_g(long n1, long n2);

        public static native long l_h(long n1, long n2);

        public static native long l_i(long n1, long n2);

        public static native long l_j(long n1, long n2);

        public static native float f_a(float n1, float n2);

        public static native float f_b(float n1, float n2);

        public static native float f_c(float n1, float n2);

        public static native float f_d(float n1, float n2);

        public static native float f_e(float n1, float n2);

        public static native double d_a(double n1, double n2);

        public static native double d_b(double n1, double n2);

        public static native double d_c(double n1, double n2);

        public static native double d_d(double n1, double n2);

        public static native double d_e(double n1, double n2);

    }

    public static class Microsoft {

        public static native MicrosoftAuthResult loginWithMicrosoft() throws MicrosoftAuthException;

        public static native MicrosoftAuthResult loginWithToken(String refreshToken) throws MicrosoftAuthException;

    }

}
