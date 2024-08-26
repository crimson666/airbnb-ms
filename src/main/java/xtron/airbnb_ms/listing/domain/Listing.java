package xtron.airbnb_ms.listing.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import xtron.airbnb_ms.sharedkernel.domain.AbstractAuditingEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="listing")
public class Listing extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listingSequenceGenerator")
    @SequenceGenerator(name = "listingSequenceGenerator", sequenceName = "listing_generator", allocationSize = 1)
    @Column(name = "id")
    private Long id;


    @UuidGenerator
    @Column(name = "public_id", nullable = false)
    private UUID publicId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "guests")
    private int guests;
    @Column(name = "bedrooms")
    private int bedrooms;
    @Column(name = "beds")
    private int beds;
    @Column(name = "bathrooms")
    private int bathrooms;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private BookingCategory bookingCategory;

    @Column(name = "location")
    private String location;

    @Column(name = "landlord_public_id")
    private UUID landlordPublicId;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.REMOVE)
    private Set<ListingPicture> pictures = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Listing listing)) return false;
        return getGuests() == listing.getGuests() && getBedrooms() == listing.getBedrooms() && getBeds() == listing.getBeds() && getBathrooms() == listing.getBathrooms() && getPrice() == listing.getPrice() && Objects.equals(getTitle(), listing.getTitle()) && Objects.equals(getDescription(), listing.getDescription()) && getBookingCategory() == listing.getBookingCategory() && Objects.equals(getLocation(), listing.getLocation()) && Objects.equals(getLandlordPublicId(), listing.getLandlordPublicId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getGuests(), getBedrooms(), getBeds(), getBathrooms(), getPrice(), getBookingCategory(), getLocation(), getLandlordPublicId());
    }

    @Override
    public String toString() {
        return "Listing{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", guests=" + guests +
                ", bedrooms=" + bedrooms +
                ", beds=" + beds +
                ", bathrooms=" + bathrooms +
                ", price=" + price +
                ", bookingCategory=" + bookingCategory +
                ", location='" + location + '\'' +
                ", landlordPublicId=" + landlordPublicId +
                '}';
    }
}
