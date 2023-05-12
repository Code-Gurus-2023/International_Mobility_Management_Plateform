import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccomodationUpdateComponent } from './accomodation-update.component';

describe('AccomodationUpdateComponent', () => {
  let component: AccomodationUpdateComponent;
  let fixture: ComponentFixture<AccomodationUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccomodationUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccomodationUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
