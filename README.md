> Simple interface to access various parts of the Provided ecosystem

# Provided Library
## About
The Provided library is a powerful tool designed to provide developers with seamless integration into their projects. It simplifies the process of incorporating various functionalities by offering a range of features tailored to meet the needs of developers.

## Features
The Provided library comes with a set of features, some of which can be unlocked based on the licensing agreement. The available features include:

### Killswitches
The Killswitch feature allows developers to define multiple endpoints that will be regularly checked. By monitoring these endpoints, the application can be shutdown while running to prevent malicious activities.

### Api
With this feature, your application can access the webapi of Provided.
This includes features such as authentication and handling of assets and their items.

### Obfuscation
This features allows developers to encrypt their strings before shipping and decrypt them at runtime. Simply pass your value and encryption key to receive the encrypted String.

### Security
This feature guaratees that the library can only be loaded within an Provided environment.
We highly recommend using this together with string encryption in order to prevent a decryption procedure from third-party.

### Microsoft Login
Enhance your users experience by logging in via Microsoft services and restoring previous sessions with refresh tokens.
This will spin up a local webserver on the users machine to process the response and provide your software with all required parameters.

Please note that the availability of these features may depend on the specific licensing agreement for Provided.

## Inclusion

### Maven via Github Packages
1. Consult the [Github Docs](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#authenticating-to-github-packages) on how to authenticate to their package registry.
2. Add `provided-library` as dependency
```xml
<dependency>
    <groupId>space.provided</groupId>
    <artifactId>provided-library</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Maven via Jitpack
1. Add Jitpack as repository to your `pom.xml`
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
2. Add `provided-library` as dependency
```xml
<dependencies>
    <dependency>
        <groupId>com.github.provided-space</groupId>
        <artifactId>provided-library</artifactId>
        <version>main-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Integration
To integrate the library into your application, make sure that the binary is present in `java.library.path`.
For applications running in the Provided launcher, this is already ensured.

Initialize the library by calling the initialize method. This method loads the library and performs necessary initialization steps. It takes two parameters: payload and signature.
* Both of these values will be given to you by the libraries maintainers.
* Only subscribed features can be called through the api.
* If the payload is invalid or its integrity cannot be guaranteed, this method will throw an InitializationException
```java
try {
    Provided.initialize(payload, signature);
} catch (InitializationException e) {
    // Handle initialization exception
}
```

### API
To retrieve a user object for authentication, use the getUser method to return a ProvidedUser object.
```java
try {
    final ProvidedUser user = Provided.getUser();
    // Use the user object for further processing
} catch (ApiException e) {
    // Handle exception
}
```

To redeem a token for a user, use the redeemToken method. It takes a token parameter and returns a ProvidedUser object.
```java
try {
    final ProvidedUser user = Provided.redeemToken(token);
    // Use the user object for further processing
} catch (ApiException e) {
    // Handle exception
}
```

To get an array of asset items for a given asset ID, use the getAssetItems method. It takes an assetId parameter and returns an array of AssetItem objects.
```java
try {
    final AssetItem[] assetItems = Provided.getAssetItems(assetId);
    // Process the asset items as needed
} catch (ApiException e) {
    // Handle exception
}
```

To get the contents of an asset item by its item ID, use the getAssetItemContents method. It takes an itemId parameter and returns the contents as a string.
```java
try {
    final String assetItemContents = Provided.getAssetItemContents(itemId);
    // Process the asset item contents as needed
} catch (ApiException e) {
    // Handle exception
}
```

To upload contents for an asset item, call uploadAssetItem along with the ID of the asset, item name and contents.
```java
try {
    final AssetItem uploadedAssetItem = Provided.uploadAssetItem(assetId, name, contents);
    // Process the uploaded asset item as needed
} catch (ApiException e) {
    // Handle exception
}
```

To delete an asset item, call deleteAssetItem along with the item ID.
```java
try {
    Provided.deleteAssetItem(itemId);
} catch (ApiException e) {
    // Handle exception
}
```

### Obfuscation
To encrypt Strings in the bytecode, call encrypt with the value and a random generated String.
```java
/**
 * Allowed key sizes: 16, 24, 32
 */
final String key = generateEncryptionKey();
final String encryptedValue = Provided.Obfuscation.encrypt(stringLdc.value, key);

// add method instruction to Provided.Obfuscation.decrypt with the encrypted value and generated key as parameter
```

### Microsoft Login
To login via Microsoft, you have two options. Either open the login page in the browser and await a redirect to localhost, or use an existing refresh token.
```java
try {
    final MicrosoftAuthResult result = Provided.Microsoft.loginWithMicrosoft();
    // do something with the result
} catch (MicrosoftAuthException e) {
    // Handle exception
}

try {
    final MicrosoftAuthResult result = Provided.Microsoft.loginWithToken(refreshToken);
    // do something with the result
} catch (MicrosoftAuthException e) {
    // Handle exception
}
```

Please make sure to handle any exceptions that may be thrown by the library methods and provide appropriate error handling and feedback to the users of your application.
