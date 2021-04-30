import React from 'react';
import { useHistory } from 'react-router';
import './Feedback.css';


const Feedback = () => {
  const history = useHistory();

  const handleClick = () => {
    history.push('./');
};

  return (
    <div className="feedback-container">
      <p classname="feedback-text">
        Thank you for your purchase!
      </p>
      <button className="feedback-button" onClick={handleClick}>Home</button>
    </div>
  )
}

export default Feedback;
