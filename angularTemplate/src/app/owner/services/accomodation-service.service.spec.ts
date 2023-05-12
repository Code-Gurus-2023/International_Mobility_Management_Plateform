import { TestBed } from '@angular/core/testing';

import { AccomodationServiceService } from './accomodation-service.service';

describe('AccomodationServiceService', () => {
  let service: AccomodationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccomodationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
