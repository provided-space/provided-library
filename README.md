> Simple interface to access various parts of the Provided ecosystem

# Provided Library
## About
The Provided library is a powerful tool designed to provide developers with seamless integration into their projects. It simplifies the process of incorporating various functionalities by offering a range of features tailored to meet the needs of developers.

## Add dependency
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.provided-space:provided-library:v1.3.2'
}
```

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
This feature guarantees that the library can only be loaded within a Provided environment.
We highly recommend using this together with string encryption in order to prevent a decryption procedure from third-party.

### Microsoft Login
Enhance your users experience by logging in via Microsoft services and restoring previous sessions with refresh tokens.
This will spin up a local webserver on the users machine to process the response and provide your software with all required parameters.

Please note that the availability of these features may depend on the specific licensing agreement for Provided.

## Integration
The payload and signature to initialize the library, will be provided to you by the administrators of provided.space.
If you choose to have an expiry date set per payload, then it is your responsibility to request a new payload and signature on time.

Make sure to handle the exceptions thrown by the library methods appropriately and give your users meaningful feedback to optimize their experience.

```java
package space.provided.sample;

import org.json.JSONObject;
import space.provided.api.ApiException;
import space.provided.api.ProvidedUser;
import space.provided.natives.Provided;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;

/**
 * Copy this functionality into your application where needed and change all dynamic data (app name constant, payload and signature).
 */
public final class ProvidedExample {

    private static final String APPLICATION_NAME = "sample";
    private static final Signature VERIFIER;

    static {
        final byte[] publicKey = Base64.getDecoder().decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0MABmJAqZciwuXDpOnBFDpd0AGBalvHFTT8sGk2PkQUysRcCBm5LPrHAzR8fZccqiYGVnQhnp/EHiTleDIjuiuV/bTksXdjcnYlokoJ+n1mze+tpOtnFhBgMExp9TdEt85Nue/Ai/OSkQ/goOq+Xohv1j7kvlS3J5cUYhbBOi2rGXkhSXozqVScHIoHUaA9+MFhw3iu3OC2jBoQvy8EWhJOShWvgsJZvZl/bW1jsJzWfpLHXPXU/6HdWQQ2GGZKrJ0gT3/V0F1z8Hvmq7jADceYFiU/eIkHsnaPdLKuSCaf7KicpduXoRs38r4bujUmbjO4zH5AeCM4RUD84uMh9ZQIDAQAB");
        try {
            VERIFIER = Signature.getInstance("SHA256withRSA");
            VERIFIER.initVerify(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey)));
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SignatureException {
        loadAndInitialize("...", "...");

        try {
            final ProvidedUser user = Provided.getUser();
            validateIntegrity(user.getIntegrity());
        } catch (ApiException e) {
            final ProvidedUser user = Provided.redeemToken("...");
            validateIntegrity(user.getIntegrity());
        }
    }

    private static void loadAndInitialize(String payload, String signature) {
        final String libraryName = "provided.dll";
        final String expectedChecksum = "5b02698100143be3df0ea111512bcc491aeeb5dd472d4987f855eca88381f631";

        try {
            final InputStream inputStream = ProvidedExample.class.getClassLoader().getResourceAsStream(libraryName);
            if (inputStream == null) {
                throw new IllegalStateException("Missing %1$s as resource.".formatted(libraryName));
            }

            final byte[] libraryBytes = inputStream.readAllBytes();
            inputStream.close();

            final byte[] hash = MessageDigest.getInstance("SHA-256").digest(libraryBytes);
            final String checksum = "%064x".formatted(new BigInteger(1, hash));
            if (!checksum.equals(expectedChecksum)) {
                throw new IllegalStateException("Checksum for %1$s does not match expectation.".formatted(libraryName));
            }

            final Path nativesDirectory = Path.of(System.getenv("APPDATA"), "Provided", "applications", APPLICATION_NAME, "natives");
            Files.createDirectories(nativesDirectory);

            final Path path = nativesDirectory.resolve(libraryName);
            Files.write(path, libraryBytes);
            System.load(path.toAbsolutePath().toString());
            Provided.initialize(payload, signature);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void validateIntegrity(String integrity) throws SignatureException, SecurityException {
        final String[] parts = integrity.split("\\.");
        if (parts.length < 2) {
            throw new IllegalStateException("Insufficient amount of arguments to validate integrity.");
        }

        final byte[] payload = parts[0].getBytes(StandardCharsets.UTF_8);
        final byte[] signature = Base64.getDecoder().decode(parts[1]);
        VERIFIER.update(payload);
        if (!VERIFIER.verify(signature)) {
            throw new SecurityException("Integrity couldn't be verified due to invalid signature.");
        }

        final long timestamp = Instant.now().getEpochSecond();
        final JSONObject data = new JSONObject(new String(Base64.getDecoder().decode(payload)));
        if (!data.has("revalidate") || data.getLong("revalidate") < timestamp) {
            throw new RuntimeException("Integrity token has expired, please login again.");
        }

        // optionally check for a difference between iat (issued at) and timestamp lower than a certain threshold value.
        if (!data.has("iat") || data.getLong("iat") > timestamp) {
            throw new RuntimeException("Integrity token has been issued out of valid range, please check your system time and login again.");
        }
    }
}
```
