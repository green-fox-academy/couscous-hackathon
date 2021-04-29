import React, { useEffect, useState } from 'react';
import ImageGallery from 'react-image-gallery';
import 'react-image-gallery/styles/css/image-gallery.css';

const ImagesGallery = ({ imageList }) => {
  const [images, setImages] = useState(null);

  useEffect(() => {
    let shouldCancel = false;

    const call = () => {
      if (!shouldCancel && imageList && imageList.length > 0) {
        setImages(
          imageList.map(url => ({
            original: `${url}`,
            thumbnail: `${url}`
          }))
        );
      }
    };
    call();
    return () => (shouldCancel = true);
  }, [imageList]);

  return images ? <ImageGallery items={images} /> : null;
};

export default ImagesGallery;
