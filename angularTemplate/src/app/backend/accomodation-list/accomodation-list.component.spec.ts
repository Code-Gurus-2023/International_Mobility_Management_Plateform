import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccomodationListComponent } from './accomodation-list.component';

describe('AccomodationListComponent', () => {
  let component: AccomodationListComponent;
  let fixture: ComponentFixture<AccomodationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccomodationListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccomodationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
