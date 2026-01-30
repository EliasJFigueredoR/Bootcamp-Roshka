/* services/books.service.ts
export const getBooks = async (): Promise<Book[]> => {
  const { data } = await axiosInstance.get("/books");
  
  // Aquí usamos la función para asegurarnos de que lo que llegó de Java es correcto
  return validateApiResponse(z.array(bookSchema), data);
};

 */