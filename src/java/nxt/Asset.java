package nxt;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Asset {

    private static final ConcurrentMap<Long, Asset> assets = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, Asset> assetNameToAssetMappings = new ConcurrentHashMap<>();
    private static final Collection<Asset> allAssets = Collections.unmodifiableCollection(assets.values());

    public static Collection<Asset> getAllAssets() {
        return allAssets;
    }

    public static Asset getAsset(Long id) {
        return assets.get(id);
    }

    public static Asset getAsset(String name) {
        return assetNameToAssetMappings.get(name);
    }

    static void addAsset(Long assetId, Long senderAccountId, String name, String description, int quantity) {
        Asset asset = new Asset(assetId, senderAccountId, name, description, quantity);
        Asset.assets.put(assetId, asset);
        Asset.assetNameToAssetMappings.put(name.toLowerCase(), asset);
    }

    static void clear() {
        assets.clear();
        assetNameToAssetMappings.clear();
    }

    private final Long assetId;
    private final Long accountId;
    private final String name;
    private final String description;
    private final int quantity;

    private Asset(Long assetId, Long accountId, String name, String description, int quantity) {
        this.assetId = assetId;
        this.accountId = accountId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Long getAssetId() {
        return assetId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }
}
