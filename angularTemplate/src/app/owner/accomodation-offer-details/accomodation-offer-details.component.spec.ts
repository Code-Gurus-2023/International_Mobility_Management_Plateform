import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccomodationOfferDetailsComponent } from './accomodation-offer-details.component';

describe('AccomodationOfferDetailsComponent', () => {
  let component: AccomodationOfferDetailsComponent;
  let fixture: ComponentFixture<AccomodationOfferDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccomodationOfferDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccomodationOfferDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
