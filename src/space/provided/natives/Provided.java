package space.provided.natives;

import space.provided.api.AssetItem;
import space.provided.api.ApiException;
import space.provided.api.ProvidedUser;

public final class Provided {

    static {
        System.loadLibrary("provided");
    }

    public static native void initialize(String payload, String signature) throws InitializationException;

    public static native ProvidedUser getUser() throws ApiException;

    public static native ProvidedUser redeemToken(String token) throws ApiException;

    public static native AssetItem[] getAssetItems(int assetId) throws ApiException;

    public static native String getAssetItemContents(int itemId) throws ApiException;

    public static native AssetItem uploadAssetItem(int assetId, String name, String contents) throws ApiException;

    public static native void deleteAssetItem(int itemId) throws ApiException;

    public static native String encrypt(String value, String key);

    public static native String decrypt(String value, String key);
}
