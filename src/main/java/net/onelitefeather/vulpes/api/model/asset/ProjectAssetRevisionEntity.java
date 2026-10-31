package net.onelitefeather.vulpes.api.model.asset;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import net.onelitefeather.vulpes.api.model.IdentifiableEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * One content version of a {@link ProjectAssetEntity}. Revisions are immutable: a content write adds the
 * next revision, and nothing changes an earlier one.
 *
 * <p>The bytes of a revision live under {@link #getStorageKey()}. Revision 1 of an asset created before
 * revisions existed points to the legacy key of the asset. The newest revision is the current one.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.5.0
 */
@Entity
@Table(
        name = "project_asset_revision",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_project_asset_revision_number",
                columnNames = {"asset_id", "number"}
        )
)
public class ProjectAssetRevisionEntity extends IdentifiableEntity {

    /**
     * The longest comment a revision can carry.
     */
    public static final int COMMENT_MAX_LENGTH = 500;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "asset_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectAssetEntity asset;

    /** The position of the revision in the history of its asset, starting at 1. */
    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "sha1", nullable = false, length = 40)
    private String sha1;

    @Column(name = "size_bytes", nullable = false)
    private long size;

    @Column(name = "storage_key", nullable = false, length = 255)
    private String storageKey;

    @Nullable
    @Column(name = "comment", length = COMMENT_MAX_LENGTH)
    private String comment;

    /**
     * Creates an empty entity, required by JPA. Use the setters to fill it.
     */
    public ProjectAssetRevisionEntity() {
    }

    public ProjectAssetEntity getAsset() {
        return asset;
    }

    public void setAsset(ProjectAssetEntity asset) {
        this.asset = asset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public void setStorageKey(String storageKey) {
        this.storageKey = storageKey;
    }

    public @Nullable String getComment() {
        return comment;
    }

    public void setComment(@Nullable String comment) {
        this.comment = comment;
    }
}
