import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import ConfirmationBox from './ConfirmationBox';
import '@testing-library/jest-dom/extend-expect';

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

  it('displays the Confirm and Close buttons', () => {
    render(<ConfirmationBox {...defaultProps} />);

    // Check for the presence of "Confirm" and "Close" texts in the buttons
    expect(screen.getByText('Confirm')).toBeInTheDocument();
    expect(screen.getByText('Close')).toBeInTheDocument();
  });

  it('calls onClickConfirm with true when the Confirm button is clicked', () => {
    render(<ConfirmationBox {...defaultProps} />);

    // Simulate clicking the Confirm button
    const confirmButton = screen.getByText('Confirm');
    fireEvent.click(confirmButton);

    // Check that onClickConfirm is called with true
    expect(mockOnClickConfirm).toHaveBeenCalledWith(true);
  });

  it('calls dialogHandler with false when the Close button is clicked', () => {
    render(<ConfirmationBox {...defaultProps} />);

    // Simulate clicking the Close button
    const closeButton = screen.getByText('Close');
    fireEvent.click(closeButton);

    // Check that dialogHandler is called with false
    expect(mockDialogHandler).toHaveBeenCalledWith(false);
  });
});
