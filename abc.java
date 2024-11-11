import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import ConfirmationBox from './ConfirmationBox';
import '@testing-library/jest-dom/extend-expect';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { Colors } from '../../common/constants/ColorConstants';

describe('ConfirmationBox Component', () => {
  const mockOnClickConfirm = jest.fn();
  const mockDialogHandler = jest.fn();

  const defaultProps = {
    open: true,
    title: 'Confirm Action',
    body: 'Are you sure you want to proceed?',
    onClickConfirm: mockOnClickConfirm,
    dialogHandler: mockDialogHandler,
  };

  const renderWithTheme = (mode: 'light' | 'dark') => {
    const theme = createTheme({ palette: { mode } });
    render(
      <ThemeProvider theme={theme}>
        <ConfirmationBox {...defaultProps} />
      </ThemeProvider>
    );
  };

  it('displays the Confirm and Close buttons', () => {
    renderWithTheme('light');
    expect(screen.getByText('Confirm')).toBeInTheDocument();
    expect(screen.getByText('Close')).toBeInTheDocument();
  });

  it('calls onClickConfirm with true when the Confirm button is clicked', () => {
    renderWithTheme('light');
    fireEvent.click(screen.getByText('Confirm'));
    expect(mockOnClickConfirm).toHaveBeenCalledWith(true);
  });

  it('calls dialogHandler with false when the Close button is clicked', () => {
    renderWithTheme('light');
    fireEvent.click(screen.getByText('Close'));
    expect(mockDialogHandler).toHaveBeenCalledWith(false);
  });

  it('applies dbPinkBlueGradient in light mode and dbGreenBlueGradient in dark mode for Confirm button', () => {
    // Light mode
    renderWithTheme('light');
    const confirmButton = screen.getByText('Confirm');
    expect(confirmButton).toHaveStyle(`background: ${Colors.dbPinkBlueGradient}`);
    
    // Rerender in dark mode
    renderWithTheme('dark');
    const darkConfirmButton = screen.getByText('Confirm');
    expect(darkConfirmButton).toHaveStyle(`background: ${Colors.dbGreenBlueGradient}`);
  });

  it('changes Confirm button hover background to dbBluePinkGradient in light mode and dbBlueGreenGradient in dark mode', () => {
    renderWithTheme('light');
    const confirmButton = screen.getByText('Confirm');

    // Simulate hover style by applying :hover styles if available or as expected by the condition.
    expect(confirmButton).toHaveStyle(`:hover { background: ${Colors.dbBluePinkGradient} }`);

    renderWithTheme('dark');
    const darkConfirmButton = screen.getByText('Confirm');
    expect(darkConfirmButton).toHaveStyle(`:hover { background: ${Colors.dbBlueGreenGradient} }`);
  });
});
