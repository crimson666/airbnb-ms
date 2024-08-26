package xtron.airbnb_ms.listing.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xtron.airbnb_ms.sharedkernel.domain.AbstractAuditingEntity;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="listing_picture")
public class ListingPicture extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listingPictureSequenceGenerator")
    @SequenceGenerator(name = "listingPictureSequenceGenerator", sequenceName = "listing_picture_generator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "listing_fk", referencedColumnName = "id")
    private Listing listing;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Column(name = "cover")
    private boolean isCover;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListingPicture that)) return false;
        return isCover() == that.isCover() && Arrays.equals(getFile(), that.getFile()) && Objects.equals(getFileContentType(), that.getFileContentType());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getFileContentType(), isCover());
        result = 31 * result + Arrays.hashCode(getFile());
        return result;
    }

    @Override
    public String toString() {
        return "ListingPicture{" +
                "file=" + Arrays.toString(file) +
                ", fileContentType='" + fileContentType + '\'' +
                ", isCover=" + isCover +
                '}';
    }
}
