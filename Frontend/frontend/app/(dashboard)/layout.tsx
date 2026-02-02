import ProtectedRoute from "@/components/auth/ProtectedRoutes";


export default function RootLayout({ children,}: Readonly<{children: React.ReactNode;}>) {
  return (
        <ProtectedRoute>
            <div>
                {children}
            </div>
        </ProtectedRoute>
  );
}
