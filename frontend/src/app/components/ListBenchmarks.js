'use client';

import { useState, useEffect } from 'react';
import axios from 'axios';

import { useRouter } from 'next/navigation';

const ListBenchmarks = () => {
    const [benchmarks, setBenchmarks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const router = useRouter();
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [selectedBenchmark, setSelectedBenchmark] = useState(null);
    const [deleteError, setDeleteError] = useState(null);
    const [deleteLoading, setDeleteLoading] = useState(false);

    useEffect(() => {
        loadBenchmarks();
    }, []);

    useEffect(() => {
        if (showDeleteModal) {
            document.body.style.overflow = 'hidden';
            document.body.classList.add('modal-open');
        } else {
            document.body.style.overflow = 'unset';
            document.body.classList.remove('modal-open');
        }

        return () => {
            document.body.style.overflow = 'unset';
            document.body.classList.remove('modal-open');
        };
    }, [showDeleteModal]);

    const loadBenchmarks = async () => {
        try {
            setLoading(true);
            const response = await axios.get('/api/benchmarks');
            setBenchmarks(response.data);
            setError(null);
        } catch (err) {
            setError('Error fetching benchmarks: ' + err.message);
        } finally {
            setLoading(false);
        }
    };

    const handleDeleteClick = (benchmark) => {
        setSelectedBenchmark(benchmark);
        setShowDeleteModal(true);
        setDeleteError(null);
    };

    const handleDeleteConfirm = async () => {
        try {
            setDeleteLoading(true);
            setDeleteError(null);
            
            await axios.delete(`/api/benchmarks/${selectedBenchmark.id}`);
            
            setShowDeleteModal(false);
            await loadBenchmarks();
        } catch (err) {
            setDeleteError(err.response?.data?.message || 'Erro ao deletar o benchmark');
        } finally {
            setDeleteLoading(false);
        }
    };

    if (loading) {
        return (
            <div className="container mt-4">
                <div className="text-center">
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Carregando...</span>
                    </div>
                </div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="container mt-4">
                <div className="alert alert-danger" role="alert">
                    {error}
                </div>
            </div>
        );
    }

    return (
        <div className="container mt-4">
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h2>Lista de Benchmarks</h2>
                <button
                    className="btn btn-primary"
                    onClick={() => router.push('/register')}
                >
                    Novo Benchmark
                </button>
            </div>
            <table className="table table-striped table-hover">
                <thead className="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Pais 1</th>
                        <th>Pais 2</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {benchmarks.map((benchmark) => (
                        <tr key={benchmark.id}>
                            <td>{benchmark.id}</td>
                            <td>{benchmark.name}</td>
                            <td>{benchmark.country1}</td>
                            <td>{benchmark.country2}</td>
                            <td>
                                <button
                                    className="btn btn-primary btn-sm me-2"
                                    onClick={() => router.push(`/edit/${benchmark.id}`)}
                                >
                                    Editar
                                </button>
                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => handleDeleteClick(benchmark)}
                                >
                                    Deletar
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {showDeleteModal && (
                <>
                    <div className="modal show d-block" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }} tabIndex="-1">
                        <div className="modal-dialog">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <h5 className="modal-title">Confirmar Exclusão</h5>
                                    <button 
                                        type="button" 
                                        className="btn-close" 
                                        onClick={() => setShowDeleteModal(false)}
                                        disabled={deleteLoading}
                                    ></button>
                                </div>
                                <div className="modal-body">
                                    <p>Tem certeza que deseja excluir o benchmark "{selectedBenchmark?.name}"?</p>
                                    {deleteError && (
                                        <div className="alert alert-danger">
                                            {deleteError}
                                        </div>
                                    )}
                                </div>
                                <div className="modal-footer">
                                    <button 
                                        type="button" 
                                        className="btn btn-secondary" 
                                        onClick={() => setShowDeleteModal(false)}
                                        disabled={deleteLoading}
                                    >
                                        Cancelar
                                    </button>
                                    <button 
                                        type="button" 
                                        className="btn btn-danger"
                                        onClick={handleDeleteConfirm}
                                        disabled={deleteLoading}
                                    >
                                        {deleteLoading ? (
                                            <>
                                                <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                                Excluindo...
                                            </>
                                        ) : 'Confirmar Exclusão'}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </>
            )}
        </div>
    );
};

export default ListBenchmarks;