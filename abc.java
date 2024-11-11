import React from 'react';
import { render, screen } from '@testing-library/react';
import UsageBarChart from './UsageBarChart';
import { CommonConstants } from '../../common/CommonConstants';
import { ChartColorConstant } from '../../common/constants/ColorConstants';
import { act } from 'react-dom/test-utils';

describe('UsageBarChart Component', () => {
  const mockTenantUsageData = {
    "Month:1": {
      MONTH: '2023-01-01',
      DOC_AI: { TOTAL_USAGE_UNITS: 150 },
      NLP: { TOTAL_USAGE_UNITS: 100 }
    },
    "Month:2": {
      MONTH: '2023-02-01',
      DOC_AI: { TOTAL_USAGE_UNITS: 200 },
      NLP: { TOTAL_USAGE_UNITS: 150 }
    },
  };

  it('initializes usageData state as an empty array', () => {
    const { result } = render(<UsageBarChart tenantUsageData={{}} />);
    const usageData = result.current.usageData;
    expect(usageData).toEqual([]);
  });

  it('formats the date correctly using calculateMonth function', () => {
    const calculateMonth = (date: Date) => {
      const all_months = [
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"
      ];
      const newDate = new Date(date);
      const month = all_months[newDate.getMonth()] + ' ' + newDate.getFullYear();
      return month;
    };

    const date = new Date('2023-01-01');
    expect(calculateMonth(date)).toBe('Jan 2023');
  });

  it('updates usageData state correctly based on tenantUsageData prop', () => {
    const { rerender } = render(<UsageBarChart tenantUsageData={mockTenantUsageData} />);
    act(() => {
      rerender(<UsageBarChart tenantUsageData={mockTenantUsageData} />);
    });

    const expectedUsageData = [
      { month: 'Jan 2023', 'Doc AI': 150, NLP: 100 },
      { month: 'Feb 2023', 'Doc AI': 200, NLP: 150 },
    ];

    expect(screen.getByTestId('usageBarChart')).toHaveProperty('usageData', expectedUsageData);
  });

  it('renders chart elements with expected data', () => {
    render(<UsageBarChart tenantUsageData={mockTenantUsageData} />);

    expect(screen.getByText('Month-wise units for each service by a particular tenant')).toBeInTheDocument();
    expect(screen.getByText('Jan 2023')).toBeInTheDocument();
    expect(screen.getByText('Feb 2023')).toBeInTheDocument();
  });

  it('renders XAxis and YAxis labels with correct constants', () => {
    render(<UsageBarChart tenantUsageData={mockTenantUsageData} />);

    expect(screen.getByText(CommonConstants.STACKED_BAR_XAXIS_LABEL)).toBeInTheDocument();
    expect(screen.getByText(CommonConstants.STACKED_BAR_YAXIS_LABEL)).toBeInTheDocument();
  });
});
